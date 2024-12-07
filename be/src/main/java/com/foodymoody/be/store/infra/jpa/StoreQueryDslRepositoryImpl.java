package com.foodymoody.be.store.infra.jpa;

import static com.foodymoody.be.feed.domain.entity.QFeed.feed;
import static com.foodymoody.be.feed.domain.entity.QImageMenu.imageMenu;
import static com.foodymoody.be.menu.domain.entity.QMenu.menu;
import static com.foodymoody.be.store.domain.QStore.store;
import static com.foodymoody.be.store_like.domain.QStoreLike.storeLike;
import static com.foodymoody.be.store_like_count.domain.QStoreLikeCount.storeLikeCount;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import com.foodymoody.be.store.domain.StoreStatus.StatusType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreQueryDslRepositoryImpl implements StoreQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<StoreDetailsResponse> fetchDetailsById(StoreId id, MemberId currentMemberId) {

        StoreDetailsResponse findById = jpaQueryFactory
                .select(Projections.constructor(
                        StoreDetailsResponse.class,
                        store.name,
                        menu.rating.avg(),
                        storeLike.count().gt(0),
                        storeLikeCount.count.count,
                        feed.countDistinct(),
                        store.address,
                        store.roadAddress,
                        store.phone,
                        store.x,
                        store.y,
                        store.status.type.eq(StatusType.CLOSED)))
                .from(store)
                .leftJoin(feed).on(store.id.eq(feed.storeId))
                .leftJoin(imageMenu).on(feed.imageMenus.imageMenusList.contains(imageMenu))
                .leftJoin(menu).on(imageMenu.menuId.eq(menu.id))
                .leftJoin(storeLike).on(storeLike.storeId.eq(id).and(eqMemberIdIfNotNull(currentMemberId)))
                .leftJoin(storeLikeCount).on(storeLikeCount.storeId.eq(id))
                .where(store.id.eq(id))
                .groupBy(store.id, storeLikeCount.count)
                .fetchOne();

        return Optional.ofNullable(findById);
    }

    @Override
    public List<StoreSearchResponse> searchByKeyword(String query) {
        return jpaQueryFactory
                .select(Projections.constructor(
                        StoreSearchResponse.class,
                        store.id,
                        store.name,
                        store.address,
                        store.roadAddress))
                .from(store)
                .where(
                        store.status.type.notIn(StatusType.CLOSED)
                                .and(
                                        store.name.contains(query)
                                                .or(store.roadAddress.contains(query))
                                                .or(store.address.contains(query))
                                )
                )
                .limit(15)
                .fetch();
    }

    private BooleanExpression eqMemberIdIfNotNull(MemberId currentMemberId) {
        if (Objects.nonNull(currentMemberId)) {
            return storeLike.memberId.eq(currentMemberId);
        }
        return Expressions.asBoolean(true).isFalse();
    }

}
