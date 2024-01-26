package com.foodymoody.be.store.domain;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import java.util.List;
import java.util.Optional;

public interface StoreRepository {

    Optional<StoreDetailsResponse> fetchDetailsById(StoreId id);

    List<StoreSearchResponse> searchByKeyword(String query);
}
