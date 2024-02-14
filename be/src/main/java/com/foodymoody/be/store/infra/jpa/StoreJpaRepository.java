package com.foodymoody.be.store.infra.jpa;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.dto.response.StoreFeedPreviewResponse;
import com.foodymoody.be.store.domain.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreJpaRepository extends JpaRepository<Store, StoreId>, StoreQueryDslRepository {

    @Query("SELECT new com.foodymoody.be.store.application.dto.response.StoreFeedPreviewResponse (f.id, i.url) "
            + "FROM Store s "
            + "LEFT JOIN Feed f ON f.storeId = s.id "
            + "INNER JOIN f.imageMenus.imageMenusList im "
            + "INNER JOIN FETCH Image i ON im.imageId = i.id "
            + "WHERE s.id = :id AND im.displayOrder = 0 "
            + "ORDER BY f.createdAt DESC")
    Slice<StoreFeedPreviewResponse> fetchStoreFeedResponses(StoreId id, Pageable pageable);
}
