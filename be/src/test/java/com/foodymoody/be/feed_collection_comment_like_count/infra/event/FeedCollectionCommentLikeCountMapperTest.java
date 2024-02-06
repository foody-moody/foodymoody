package com.foodymoody.be.feed_collection_comment_like_count.infra.event;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentAddedEvent;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCollectionCommentLikeCountMapperTest {

    @DisplayName("likeCount로 변환한다")
    @Test
    void toLikeCount() {
        // given
        var event = FeedCollectionCommentAddedEvent.of(
                IdFactory.createFeedCollectionId(),
                IdFactory.createMemberId(),
                IdFactory.createFeedCollectionCommentId(),
                new Content("content"),
                LocalDateTime.now()
        );
        var id = IdFactory.createFeedCollectionCommentLikeCountId();

        // when
        var likeCount = FeedCollectionCommentLikeCountMapper.toLikeCount(event, id);

        // then
        Assertions.assertAll(
                () -> assertThat(likeCount.getId()).isEqualTo(id),
                () -> assertThat(likeCount).hasFieldOrPropertyWithValue(
                        "feedCollectionCommentId",
                        event.getFeedCollectionCommentId()
                ),
                () -> assertThat(likeCount).hasFieldOrPropertyWithValue(
                        "count",
                        FeedCollectionCommentLikeCountMapper.INIT_COUNT
                )
        );

    }
}
