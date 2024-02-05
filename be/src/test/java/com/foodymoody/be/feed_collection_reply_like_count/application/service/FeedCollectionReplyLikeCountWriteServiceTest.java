package com.foodymoody.be.feed_collection_reply_like_count.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_reply_like_count.domain.FeedCollectionReplyLikeCount;
import com.foodymoody.be.feed_collection_reply_like_count.infra.persistence.jpa.FeedCollectionReplyLikeCountJpaRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FeedCollectionReplyLikeCountWriteServiceTest {

    @Autowired
    private FeedCollectionReplyLikeCountWriteService service;
    @Autowired
    private FeedCollectionReplyLikeCountJpaRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }


    @DisplayName("likeCount을 증가시킨다.")
    @Test
    void increaseCount() {
        // given
        var id = IdFactory.createFeedCollectionReplyLikeCountId();
        var collectionReplyId = IdFactory.createFeedCollectionReplyId();
        var likeCount = new FeedCollectionReplyLikeCount(
                id,
                collectionReplyId,
                0L,
                LocalDateTime.now()
        );
        service.save(likeCount);

        // when
        service.increaseCount(collectionReplyId);

        // then
        var saved = repository.findById(id).orElseThrow();
        assertThat(saved).hasFieldOrPropertyWithValue("count", 1L);
    }

    @DisplayName("likeCount을 감소시킨다.")
    @Test
    void decreaseCount() {
        // given
        var id = IdFactory.createFeedCollectionReplyLikeCountId();
        var collectionReplyId = IdFactory.createFeedCollectionReplyId();
        var likeCount = new FeedCollectionReplyLikeCount(
                id,
                collectionReplyId,
                1L,
                LocalDateTime.now()
        );
        service.save(likeCount);

        // when
        service.decreaseCount(collectionReplyId);

        // then
        var saved = repository.findById(id).orElseThrow();
        assertThat(saved).hasFieldOrPropertyWithValue("count", 0L);
    }
}
