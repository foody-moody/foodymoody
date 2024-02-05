package com.foodymoody.be.store_like_count.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreLikeCountId;
import com.foodymoody.be.store_like_count.domain.StoreLikeCount;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface StoreLikeCountJpaRepository extends JpaRepository<StoreLikeCount, StoreLikeCountId> {

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional
    Optional<StoreLikeCount> findByStoreId(StoreId storeId);
}
