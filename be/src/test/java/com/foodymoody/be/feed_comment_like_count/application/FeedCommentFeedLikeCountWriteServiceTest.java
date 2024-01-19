package com.foodymoody.be.feed_comment_like_count.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedCommentLikeCountId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCount;
import com.foodymoody.be.feed_comment_like_count.infra.persistence.jpa.CommentHeartCountJpaRepository;
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
@DisplayName("댓글 하트 카운트 증감 서비스 테스트")
class FeedCommentFeedLikeCountWriteServiceTest {

    @Autowired
    private CommentHeartCountWriteService commentHeartCountWriteService;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private CommentHeartCountJpaRepository commentHeartCountJpaRepository;

    @BeforeEach
    void setUp() {
        commentHeartCountJpaRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        commentHeartCountJpaRepository.deleteAll();
    }

    @DisplayName("댓글 하트 카운트를 100번 비동기로 증가시키면 100번 증가한다.")
    @Test
    void increment() {
        // given
        FeedCommentId feedCommentId = new FeedCommentId("1");
        FeedCommentLikeCountId feedCommentLikeCountId = IdFactory.createFeedCommentLikeCountId();
        commentHeartCountJpaRepository.save(new FeedCommentLikeCount(feedCommentLikeCountId, feedCommentId, 1L));
        CountDownLatch latch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                commentHeartCountWriteService.increment(feedCommentId);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // then
        Optional<FeedCommentLikeCount> heartCount = commentHeartCountJpaRepository.findByFeedCommentId(feedCommentId);
        assertThat(heartCount).isPresent();
        assertThat(heartCount.get()).hasFieldOrPropertyWithValue("count", 101L);
    }

    @DisplayName("댓글 하트 카운트를 100번 비동기로 증가시키면 100번 증가한다.")
    @Test
    void decrement() {
        // given
        FeedCommentId feedCommentId = new FeedCommentId("1");
        FeedCommentLikeCountId feedCommentLikeCountId = IdFactory.createFeedCommentLikeCountId();
        commentHeartCountJpaRepository.save(new FeedCommentLikeCount(feedCommentLikeCountId, feedCommentId, 100L));
        CountDownLatch latch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                commentHeartCountWriteService.decrement(feedCommentId);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // then
        Optional<FeedCommentLikeCount> heartCount = commentHeartCountJpaRepository.findByFeedCommentId(feedCommentId);
        assertThat(heartCount).isPresent();
        assertThat(heartCount.get()).hasFieldOrPropertyWithValue("count", 0L);
    }
}
