package com.foodymoody.be.feed_reply_like_count.infra.event;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentReplyAddedEvent;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedReplyLikeCountMapperTest {

    @DisplayName("피드 댓글 좋아요 카운트를 생성한다.")
    @Test
    void toFeedReplyLikeCount() {
        // given
        var event = FeedCommentReplyAddedEvent.of(
                IdFactory.createFeedCommentId(),
                IdFactory.createMemberId(),
                IdFactory.createFeedId(),
                IdFactory.createFeedReplyId(),
                IdFactory.createMemberId(),
                new Content("content"),
                LocalDateTime.now()
        );
        var feedReplyLikeCountId = IdFactory.createFeedReplyLikeCountId();

        // when
        var feedReplyLikeCount = FeedReplyLikeCountMapper.toFeedReplyLikeCount(
                event,
                feedReplyLikeCountId
        );

        // then
        assertThat(feedReplyLikeCount).hasFieldOrPropertyWithValue(
                "id",
                feedReplyLikeCountId
        ).hasFieldOrPropertyWithValue(
                "feedReplyId",
                event.getFeedReplyId()
        ).hasFieldOrPropertyWithValue(
                "count",
                FeedReplyLikeCountMapper.INIT_COUNT
        );
    }

}
