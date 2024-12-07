package com.foodymoody.be.store_like_count.application.service;

import com.foodymoody.be.common.exception.StoreNotFoundException;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store_like_count.domain.StoreLikeCount;
import com.foodymoody.be.store_like_count.domain.StoreLikeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreLikeCountWriteService {

    private final StoreLikeCountRepository repository;

    public void increment(StoreId storeId) {
        StoreLikeCount count = repository.findByStoreId(storeId)
                .orElseThrow(StoreNotFoundException::new);
        count.increment();
    }

    public void decrement(StoreId storeId) {
        StoreLikeCount count = repository.findByStoreId(storeId)
                .orElseThrow(StoreNotFoundException::new);
        count.decrement();
    }

}
