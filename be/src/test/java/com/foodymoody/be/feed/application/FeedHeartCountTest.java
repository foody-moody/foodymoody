package com.foodymoody.be.feed.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.repository.FeedRepository;
import com.foodymoody.be.feed_heart.domain.FeedHeart;
import com.foodymoody.be.feed_heart.repository.FeedHeartRepository;
import com.foodymoody.be.feed_heart_count.domain.FeedHeartCount;
import com.foodymoody.be.feed_heart_count.repository.FeedHeartCountRepository;
import com.foodymoody.be.feed_heart_count.service.FeedHeartCountService;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import com.foodymoody.be.utils.SpringBootIntegrationTest;
import java.util.List;
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
    private FeedHeartCountService feedHeartCountService;
    @Autowired
    private FeedHeartCountRepository feedHeartCountRepository;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @BeforeEach
    void setUp() {
        feedHeartCountRepository.deleteAll();
        // TODO: feed 등록
        feedRepository.save(new Feed(IdGenerator.generate(), "1", "위치", "리뷰", List.of("1", "2"),
                List.of(new Image("1", "https://www.naver.com")), List.of(new Menu("1", "메뉴 이름", 5))));
    }

    @AfterEach
    void tearDown() {
        feedHeartCountRepository.deleteAll();
    }

    @DisplayName("피드 좋아요를 100번 비동기로 증가시키면 하트 카운트가 100번 증가한다.")
    @Test
    void increment_count() {
        // given
        String feedId = "1";
        FeedHeartCount feedHeartCount = new FeedHeartCount(IdGenerator.generate(), feedId, 0);
        feedHeartCountRepository.save(feedHeartCount);
        CountDownLatch latch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                feedHeartCountService.incrementFeedHeartCount(feedId);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // then
        Optional<FeedHeartCount> result = feedHeartCountRepository.findByFeedId(feedId);
        assertThat(result).isPresent();
        assertThat(result.get().getCount()).isEqualTo(101L);
    }

    @DisplayName("피드 좋아요를 100번 비동기로 감소시키면 하트 카운트가 100번 감소한다.")
    @Test
    void decrement_count() {
        // given
        String feedId = "1";
        FeedHeartCount feedHeartCount = new FeedHeartCount(IdGenerator.generate(), feedId, 100);
        feedHeartCountRepository.save(feedHeartCount);
        CountDownLatch latch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                feedHeartCountService.incrementFeedHeartCount(feedId);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // then
        Optional<FeedHeartCount> result = feedHeartCountRepository.findByFeedId(feedId);
        assertThat(result).isPresent();
        assertThat(result.get().getCount()).isZero();
    }

}
