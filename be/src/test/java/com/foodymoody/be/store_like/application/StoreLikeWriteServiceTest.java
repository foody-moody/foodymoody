package com.foodymoody.be.store_like.application;

import static org.assertj.core.api.Assertions.*;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store_like_count.application.service.StoreLikeCountWriteService;
import com.foodymoody.be.store_like_count.domain.StoreLikeCount;
import com.foodymoody.be.store_like_count.infra.persistence.jpa.StoreLikeCountJpaRepository;
import com.foodymoody.be.utils.SpringBootIntegrationTest;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@SpringBootIntegrationTest
class StoreLikeWriteServiceTest {

    @Autowired
    private StoreLikeCountWriteService service;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private StoreLikeCountJpaRepository jpaRepository;

    @Test
    void when_increment_if_success_then_increase_1() {
        // given
        StoreId storeId = IdFactory.createStoreId();
        jpaRepository.save(StoreLikeCount.from(storeId));

        // when
        service.increment(storeId);

        // then
        StoreLikeCount actual = jpaRepository.findByStoreId(storeId).get();
        assertThat(actual.getCount()).isEqualTo(1);
    }

    @Test
    void when_increment_if_concurrent_then_throws_ObjectOptimisticLockingFailureException()
            throws InterruptedException {

        // given
        final int THREAD_COUNT = 2;
        StoreId storeId = IdFactory.createStoreId();
        jpaRepository.save(StoreLikeCount.from(storeId));
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        // when
        List<? extends Future<?>> futures = IntStream.range(0, THREAD_COUNT).mapToObj(
                        i -> threadPoolExecutor.submit(
                                () -> {
                                    try {
                                        service.increment(storeId);
                                    } finally {
                                        latch.countDown();
                                    }
                                }
                        ))
                .collect(Collectors.toUnmodifiableList());

        Exception actual = new Exception();

        try {
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (ExecutionException e) {
            actual = (Exception) e.getCause();
        }

        // then
        Assertions.assertInstanceOf(ObjectOptimisticLockingFailureException.class, actual);
    }

}
