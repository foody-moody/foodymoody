package com.foodymoody.be.store.infra;

import static com.foodymoody.be.store.domain.QStore.store;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.dto.response.StoreSearchResponse;
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
                        store.address,
                        store.roadAddress,
                        store.phone,
                        store.x,
                        store.y,
                        store.status.type.eq(StatusType.CLOSED)))
                .from(store)
                .where(store.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(findById);
    }

    @Override
    public List<StoreSearchResponse> searchByKeyword(String query) {
        StringBuilder sb = new StringBuilder();
        String pattern = sb.append("%").append(query).append("%").toString();
        return jpaQueryFactory
                .select(Projections.constructor(
                        StoreSearchResponse.class,
                        store.id,
                        store.name,
                        store.address,
                        store.roadAddress))
                .from(store)
                .where(store.name.like(pattern)
                        .or(store.roadAddress.like(pattern))
                        .or(store.address.like(pattern))
                        .and(store.status.type.notIn(StatusType.CLOSED)))
                .limit(15)
                .fetch();
    }
}
