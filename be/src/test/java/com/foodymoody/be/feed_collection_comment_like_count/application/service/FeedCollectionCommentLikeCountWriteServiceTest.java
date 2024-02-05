package com.foodymoody.be.feed_collection_comment_like_count.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_comment_like_count.domain.FeedCollectionCommentLikeCount;
import com.foodymoody.be.feed_collection_comment_like_count.infra.persistence.jpa.FeedCollectionCommentLikeCountJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FeedCollectionCommentLikeCountWriteServiceTest {

    @Autowired
    FeedCollectionCommentLikeCountWriteService service;

    @Autowired
    FeedCollectionCommentLikeCountJpaRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @DisplayName("좋아요 카운트를 증가시킨다")
    @Test
    void increase() {
        // given
        var commentId = IdFactory.createFeedCollectionCommentId();
        var id = IdFactory.createFeedCollectionCommentLikeCountId();
        var likeCount = new FeedCollectionCommentLikeCount(
                id,
                commentId,
                0L
        );
        service.save(likeCount);

        // when
        service.increase(commentId);

        // then
        var savedLickCount = repository.findById(id).orElseThrow();
        assertThat(savedLickCount).hasFieldOrPropertyWithValue("count", 1L);
    }

    @DisplayName("좋아요 카운트를 감소시킨다")
    @Test
    void decrease() {
        // given
        var commentId = IdFactory.createFeedCollectionCommentId();
        var id = IdFactory.createFeedCollectionCommentLikeCountId();
        var likeCount = new FeedCollectionCommentLikeCount(
                id,
                commentId,
                1L
        );
        service.save(likeCount);

        // when
        service.decrease(commentId);

        // then
        var savedLickCount = repository.findById(id).orElseThrow();
        assertThat(savedLickCount).hasFieldOrPropertyWithValue("count", 0L);
    }
}
