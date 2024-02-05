package com.foodymoody.be.store_like.infra.jpa;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreLikeId;
import com.foodymoody.be.store_like.domain.StoreLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLikeJpaRepository extends JpaRepository<StoreLike, StoreLikeId> {

    void deleteByStoreIdAndMemberId(StoreId storeId, MemberId memberId);

    Optional<StoreLike> findByStoreIdAndMemberId(StoreId storeId, MemberId memberId);
}
