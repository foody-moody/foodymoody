package com.foodymoody.be.store.infra;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store.application.dto.response.StoreFeedPreviewResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreDetailsResponse;
import com.foodymoody.be.store.application.service.dto.response.StoreSearchResponse;
import com.foodymoody.be.store.domain.Store;
import com.foodymoody.be.store.domain.StoreRepository;
import com.foodymoody.be.store.infra.jpa.StoreJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepository {

    private final StoreJpaRepository jpaRepository;

    @Override
    public Optional<StoreDetailsResponse> fetchDetailsById(StoreId id, MemberId currentMemberId) {
        return jpaRepository.fetchDetailsById(id, currentMemberId);
    }

    @Override
    public List<StoreSearchResponse> searchByKeyword(String query) {
        return jpaRepository.searchByKeyword(query);
    }

    @Override
    public Optional<Store> findById(StoreId id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Slice<StoreFeedPreviewResponse> fetchStoreFeedResponses(StoreId id, Pageable pageable) {
        return jpaRepository.fetchStoreFeedResponses(id, pageable);
    }

}
