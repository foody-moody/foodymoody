package com.foodymoody.be.feed_comment.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.feed_comment.application.CommentMapper;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 매퍼 테스트")
class FeedCommentMapperTest {

    @DisplayName("toEntity() 메소드는 RegisterCommentRequest 객체와 id를 인자로 받아 Comment 객체를 반환한다.")
    @Test
    void toEntity() {
        // given
        var commentMapper = new CommentMapper();
        var createdAt = CommentFixture.CREATED_AT;
        var commentId = new FeedCommentId(CommentFixture.COMMENT_ID);
        var data = CommentFixture.registerCommentData();

        // when
        FeedComment feedComment = commentMapper.toEntity(
                data,
                commentId,
                createdAt
        );

        // then
        assertThat(feedComment).isNotNull();
        Assertions.assertAll(
                () -> assertThat(feedComment.getId()).usingRecursiveComparison().isEqualTo(commentId),
                () -> assertThat(feedComment.getContent()).usingRecursiveComparison().isEqualTo(data.getContent()),
                () -> assertThat(feedComment.getFeedId()).usingRecursiveComparison().isEqualTo(data.getFeedId()),
                () -> assertThat(feedComment.getCreatedAt()).isEqualTo(createdAt),
                () -> assertThat(feedComment.getUpdatedAt()).isEqualTo(createdAt),
                () -> assertThat(feedComment.isDeleted()).isEqualTo(CommentFixture.DELETED),
                () -> assertThat(feedComment.getMemberId()).usingRecursiveComparison().isEqualTo(data.getMemberId())
        );
    }
}
