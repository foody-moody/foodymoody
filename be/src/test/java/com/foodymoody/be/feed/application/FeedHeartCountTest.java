package com.foodymoody.be.feed.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.heart.domain.Heart;
import com.foodymoody.be.heart.repository.HeartRepository;
import com.foodymoody.be.heart.service.HeartService;
import com.foodymoody.be.utils.SpringBootIntegrationTest;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootIntegrationTest
class FeedHeartCountTest {

    @Autowired
    private HeartService heartService;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private HeartRepository heartRepository;

    @BeforeEach
    void setUp() {
        heartRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        heartRepository.deleteAll();
    }

    @DisplayName("피드 하트 카운트를 100번 비동기로 증가시키면 하트 카운트가 100번 증가한다.")
    @Test
    void increment_count() {
        // given
        String memberId = "1";
        String feedId = "1";
        Heart heart = new Heart(IdGenerator.generate(), feedId, memberId);
        CountDownLatch latch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                heartRepository.incrementCount(feedId);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // then
        Optional<Heart> result = heartRepository.findByFeedId(feedId);
        assertThat(result).isPresent();
        assertThat(result.get().getCount()).isEqualTo(101L);
    }

}
