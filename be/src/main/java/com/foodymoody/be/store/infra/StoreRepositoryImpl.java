package com.foodymoody.be.store.infra;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import com.foodymoody.be.store.domain.Store;
import com.foodymoody.be.store.domain.StoreRepository;
import com.foodymoody.be.store.infra.jpa.StoreJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

    private final StoreJpaRepository jpaRepository;

    @Override
    public Optional<StoreDetailsResponse> fetchDetailsById(StoreId id) {
        return jpaRepository.fetchDetailsById(id);
    }

    @Override
    public List<StoreSearchResponse> searchByKeyword(String query) {
        return jpaRepository.searchByKeyword(query);
    }

    @Override
    public Optional<Store> findById(StoreId id) {
        return jpaRepository.findById(id);
    }
}
