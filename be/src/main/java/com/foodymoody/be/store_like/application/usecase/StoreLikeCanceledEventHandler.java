package com.foodymoody.be.store_like.application.usecase;

import com.foodymoody.be.store_like.domain.StoreLikeCanceledEvent;
import com.foodymoody.be.store_like_count.application.service.StoreLikeCountWriteFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreLikeCanceledEventHandler {

    private final StoreLikeCountWriteFacade countWriteFacade;

    @Async
    @EventListener(StoreLikeCanceledEvent.class)
    public void handle(StoreLikeCanceledEvent event) {
        countWriteFacade.decrement(event.getStoreId());
    }

}
