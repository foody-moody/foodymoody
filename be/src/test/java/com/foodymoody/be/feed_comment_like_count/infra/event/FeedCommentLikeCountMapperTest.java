package com.foodymoody.be.feed_comment_like_count.infra.event;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentAddedEvent;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCommentLikeCountMapperTest {

    @DisplayName("이벤트를 받아서 FeedCommentLikeCount 객체를 생성한다.")
    @Test
    void toFeedCommentLikeCount() {
        // given
        var event = FeedCommentAddedEvent.of(
                IdFactory.createFeedId(),
                IdFactory.createMemberId(),
                IdFactory.createFeedCommentId(),
                new Content("content"),
                LocalDateTime.now()
        );
        var feedCommentLikeCountId = IdFactory.createFeedCommentLikeCountId();

        // when
        var feedCommentLikeCount = FeedCommentLikeCountMapper.toFeedCommentLikeCount(event, feedCommentLikeCountId);

        // then
        assertThat(feedCommentLikeCount)
                .hasFieldOrPropertyWithValue("id", feedCommentLikeCountId)
                .hasFieldOrPropertyWithValue("feedCommentId", event.getFeedCommentId())
                .hasFieldOrPropertyWithValue("count", 0L);
    }
}
