package com.foodymoody.be.store.infra.jpa;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<Store, StoreId>, StoreQueryDslRepository {
}
