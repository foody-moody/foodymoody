package com.foodymoody.be.store_like_count.application.service;

import com.foodymoody.be.common.util.ids.StoreId;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreLikeCountWriteFacade {

    private final StoreLikeCountWriteService likeCountService;

    @SneakyThrows
    public void increment(StoreId storeId) {
        while (true) {
            try {
                likeCountService.increment(storeId);
                break;
            } catch (ObjectOptimisticLockingFailureException e) {
                Thread.sleep(10l);
            }
        }
    }

    @SneakyThrows
    public void decrement(StoreId storeId) {
        while (true) {
            try {
                likeCountService.decrement(storeId);
                break;
            } catch (ObjectOptimisticLockingFailureException e) {
                Thread.sleep(10l);
            }
        }
    }

}
