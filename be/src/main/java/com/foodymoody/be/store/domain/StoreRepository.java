package com.foodymoody.be.store.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.dto.response.StoreFeedPreviewResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface StoreRepository {

    Optional<StoreDetailsResponse> fetchDetailsById(StoreId id, MemberId currentMemberId);

    List<StoreSearchResponse> searchByKeyword(String query);

    Optional<Store> findById(StoreId id);

    Slice<StoreFeedPreviewResponse> fetchStoreFeedResponses(StoreId id, Pageable pageable);

}
