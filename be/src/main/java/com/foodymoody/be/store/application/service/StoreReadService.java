package com.foodymoody.be.store.application.service;

import com.foodymoody.be.common.exception.StoreNotFoundException;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import com.foodymoody.be.store.domain.StoreRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreReadService {

    private final StoreRepository storeRepository;

    public StoreDetailsResponse fetchDetails(StoreId id) {
        return storeRepository.fetchDetailsById(id)
                .orElseThrow(StoreNotFoundException::new);
    }

    public List<StoreSearchResponse> search(String query) {
        return storeRepository.searchByKeyword(query);
    }
}