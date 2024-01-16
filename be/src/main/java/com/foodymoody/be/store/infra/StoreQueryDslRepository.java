package com.foodymoody.be.store.infra;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.dto.response.StoreSearchResponse;
import java.util.List;
import java.util.Optional;

public interface StoreQueryDslRepository {

    Optional<StoreDetailsResponse> fetchDetailsById(StoreId id);

    List<StoreSearchResponse> searchByKeyword(String query);
}
