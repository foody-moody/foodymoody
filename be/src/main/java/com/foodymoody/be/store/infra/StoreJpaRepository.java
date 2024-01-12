package com.foodymoody.be.store.infra;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.domain.Store;
import com.foodymoody.be.store.domain.StoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends StoreRepository, JpaRepository<Store, StoreId>,
        StoreQueryDslRepository {
}
