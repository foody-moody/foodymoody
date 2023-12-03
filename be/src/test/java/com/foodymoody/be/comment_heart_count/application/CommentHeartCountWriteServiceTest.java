package com.foodymoody.be.comment_heart_count.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCount;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountId;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountIdFactory;
import com.foodymoody.be.comment_heart_count.infra.persistence.jpa.CommentHeartCountJpaRepository;
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
class CommentHeartCountWriteServiceTest {

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
        CommentId commentId = new CommentId("1");
        CommentHeartCountId commentHeartCountId = CommentHeartCountIdFactory.newId();
        commentHeartCountJpaRepository.save(new CommentHeartCount(commentHeartCountId, commentId, 1L));
        CountDownLatch latch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                commentHeartCountWriteService.increment(commentId);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // then
        Optional<CommentHeartCount> heartCount = commentHeartCountJpaRepository.findByCommentId(commentId);
        assertThat(heartCount).isPresent();
        assertThat(heartCount.get().getCount()).isEqualTo(101L);
    }

    @DisplayName("댓글 하트 카운트를 100번 비동기로 증가시키면 100번 증가한다.")
    @Test
    void decrement() {
        // given
        CommentId commentId = new CommentId("1");
        CommentHeartCountId commentHeartCountId = CommentHeartCountIdFactory.newId();
        commentHeartCountJpaRepository.save(new CommentHeartCount(commentHeartCountId, commentId, 100L));
        CountDownLatch latch = new CountDownLatch(100);

        // when
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(() -> {
                commentHeartCountWriteService.decrement(commentId);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // then
        Optional<CommentHeartCount> heartCount = commentHeartCountJpaRepository.findByCommentId(commentId);
        assertThat(heartCount).isPresent();
        assertThat(heartCount.get().getCount()).isZero();
    }
}
