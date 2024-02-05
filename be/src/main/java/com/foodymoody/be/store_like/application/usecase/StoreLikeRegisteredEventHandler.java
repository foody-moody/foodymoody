package com.foodymoody.be.store_like.application.usecase;

import com.foodymoody.be.store_like.domain.StoreLikeRegisteredEvent;
import com.foodymoody.be.store_like_count.application.service.StoreLikeCountWriteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreLikeRegisteredEventHandler {

    private final StoreLikeCountWriteFacade countWriteFacade;

    @Async
    @EventListener(StoreLikeRegisteredEvent.class)
    public void handle(StoreLikeRegisteredEvent event) {
        countWriteFacade.increment(event.getStoreId());
    }

}
