package com.foodymoody.be.feed_collection.infra.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CollectionReadUseCase {

    private final CollectionReadService service;

    public void fetchCollection(String memberId) {
        service.fetchCollection(memberId);
    }
}
