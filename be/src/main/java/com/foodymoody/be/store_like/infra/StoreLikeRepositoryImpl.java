package com.foodymoody.be.store_like.infra;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store_like.domain.StoreLike;
import com.foodymoody.be.store_like.domain.StoreLikeRepository;
import com.foodymoody.be.store_like.infra.jpa.StoreLikeJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreLikeRepositoryImpl implements StoreLikeRepository {

    private final StoreLikeJpaRepository jpaRepository;

    @Override
    public StoreLike save(StoreLike storeLike) {
        return jpaRepository.save(storeLike);
    }

    @Override
    public void deleteByStoreIdAndMemberId(StoreId storeId, MemberId memberId) {
        jpaRepository.deleteByStoreIdAndMemberId(storeId, memberId);
    }

    @Override
    public Optional<StoreLike> findByStoreIdAndMemberId(StoreId storeId, MemberId memberId) {
        return jpaRepository.findByStoreIdAndMemberId(storeId, memberId);
    }

}
