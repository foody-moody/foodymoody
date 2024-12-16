package com.foodymoody.be.store_like.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.store_like_count.application.service.StoreLikeCountWriteFacade;
import com.foodymoody.be.store_like_count.domain.StoreLikeCount;
import com.foodymoody.be.store_like_count.infra.persistence.jpa.StoreLikeCountJpaRepository;
import com.foodymoody.be.utils.SpringBootIntegrationTest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootIntegrationTest
class StoreLikeCountWriteFacadeTest {

    @Autowired
    private StoreLikeCountWriteFacade facade;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    private StoreLikeCountJpaRepository jpaRepository;

    @BeforeEach
    void setup() {
        jpaRepository.deleteAll();
    }

    @Test
    void when_concurrent_increment_100_times_if_success_then_increase_100() throws InterruptedException {
        // given
        final int THREAD_COUNT = 100;
        StoreId storeId = new StoreId("1");
        jpaRepository.save(StoreLikeCount.from(storeId));
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        // when
        IntStream.range(0, THREAD_COUNT)
                .forEach(
                        i -> {
                            threadPoolExecutor.submit(() ->
                            {
                                try {
                                    facade.increment(storeId);
                                } finally {
                                    latch.countDown();
                                }
                            });
                        }
                );

        latch.await();

        // then
        StoreLikeCount actual = jpaRepository.findByStoreId(storeId).get();
        assertThat(actual.getCount()).isEqualTo(THREAD_COUNT);

    }

}
