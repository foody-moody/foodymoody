package com.foodymoody.be.store.infra.jpa;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface StoreQueryDslRepository {

    Optional<StoreDetailsResponse> fetchDetailsById(StoreId id);

    List<StoreSearchResponse> searchByKeyword(String query);
}
