package com.foodymoody.be.feed_collection_comment.application;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.IdFactory;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedCollectionCommentMapperTest {

    @DisplayName("FeedCollectionComment 엔티티로 변환한다.")
    @Test
    void toEntity() {
        // given
        var createdAt = LocalDateTime.now();
        var feedCollectionId = IdFactory.createFeedCollectionId();
        var content = new Content("content");
        var memberId = IdFactory.createMemberId();
        var feedCollectionCommentId = IdFactory.createFeedCollectionCommentId();

        // when
        var comment = FeedCollectionCommentMapper.toEntity(
                feedCollectionId,
                content,
                memberId,
                feedCollectionCommentId,
                createdAt
        );

        // then
        assertThat(comment.getId()).isEqualTo(feedCollectionCommentId);
        assertThat(comment.getFeedCollectionId()).isEqualTo(feedCollectionId);
        assertThat(comment.getMemberId()).isEqualTo(memberId);
        assertThat(comment.getContent()).isEqualTo(content);
        assertThat(comment).hasFieldOrPropertyWithValue("createdAt", createdAt);
    }

}
