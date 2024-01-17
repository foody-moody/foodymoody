package com.foodymoody.be.store.infra.jpa;

import static com.foodymoody.be.feed.domain.entity.QFeed.*;
import static com.foodymoody.be.store.domain.QStore.store;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import com.foodymoody.be.store.domain.StoreStatus.StatusType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreQueryDslRepositoryImpl implements StoreQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<StoreDetailsResponse> fetchDetailsById(StoreId id) {
        StoreDetailsResponse findById = jpaQueryFactory
                .select(Projections.constructor(
                        StoreDetailsResponse.class,
                        store.name,
                        feed.count(),
                        store.address,
                        store.roadAddress,
                        store.phone,
                        store.x,
                        store.y,
                        store.status.type.eq(StatusType.CLOSED)))
                .from(store)
                .leftJoin(feed).on(store.id.eq(feed.storeId))
                .where(store.id.eq(id))
                .groupBy(store.id)
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
}
