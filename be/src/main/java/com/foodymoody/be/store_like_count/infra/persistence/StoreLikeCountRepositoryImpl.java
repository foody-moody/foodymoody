package com.foodymoody.be.store_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store_like_count.domain.StoreLikeCount;
import com.foodymoody.be.store_like_count.domain.StoreLikeCountRepository;
import com.foodymoody.be.store_like_count.infra.persistence.jpa.StoreLikeCountJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreLikeCountRepositoryImpl implements StoreLikeCountRepository {

    private final StoreLikeCountJpaRepository jpaRepository;

    @Override
    public Optional<StoreLikeCount> findByStoreId(StoreId storeId) {
        return jpaRepository.findByStoreId(storeId);
    }

    @Override
    public StoreLikeCount save(StoreLikeCount storeLikeCount) {
        return jpaRepository.save(storeLikeCount);
    }
}
