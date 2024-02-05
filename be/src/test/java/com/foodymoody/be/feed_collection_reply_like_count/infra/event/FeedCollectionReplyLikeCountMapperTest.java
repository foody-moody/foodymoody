package com.foodymoody.be.feed_collection_reply_like_count.infra.event;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyAddedEvent;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCollectionReplyLikeCountMapperTest {

    @DisplayName("likeCount 매핑 테스트")
    @Test
    void toLikeCount() {
        // given
        var event = FeedCollectionReplyAddedEvent.of(
                IdFactory.createFeedCollectionCommentId(),
                IdFactory.createMemberId(),
                IdFactory.createFeedCollectionReplyId(),
                new Content("content"),
                LocalDateTime.now()
        );
        var id = IdFactory.createFeedCollectionReplyLikeCountId();

        // when
        var likeCount = FeedCollectionReplyLikeCountMapper.toLikeCount(event, id);

        // then
        Assertions.assertAll(
                () -> assertThat(likeCount).isNotNull(),
                () -> assertThat(likeCount.getId()).isEqualTo(id),
                () -> assertThat(likeCount).hasFieldOrPropertyWithValue(
                        "feedCollectionReplyId", event.getFeedCollectionReplyId()),
                () -> assertThat(likeCount).hasFieldOrPropertyWithValue("createdAt", event.getCreatedAt()),
                () -> assertThat(likeCount).hasFieldOrPropertyWithValue("updatedAt", event.getCreatedAt()),
                () -> assertThat(likeCount).hasFieldOrPropertyWithValue("count", 0L)
        );
    }

}
