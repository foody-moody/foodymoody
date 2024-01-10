package com.foodymoody.be.comment.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.application.CommentMapper;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.common.util.ids.CommentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 매퍼 테스트")
class CommentMapperTest {

    @DisplayName("toEntity() 메소드는 RegisterCommentRequest 객체와 id를 인자로 받아 Comment 객체를 반환한다.")
    @Test
    void toEntity() {
        // given
        var request = CommentFixture.registerCommentRequest();
        var commentMapper = new CommentMapper();
        var feedId = CommentFixture.comment().getFeedId();
        var createdAt = CommentFixture.CREATED_AT;
        var commentId = new CommentId(CommentFixture.COMMENT_ID);
        var memberId = CommentFixture.memberId();

        // when
        Comment comment = commentMapper.toEntity(
                request,
                feedId,
                createdAt,
                commentId,
                memberId
        );

        // then
        Assertions.assertAll(
                () -> assertThat(comment).isNotNull(),
                () -> assertThat(comment.getId()).usingRecursiveComparison().isEqualTo(CommentFixture.commentId()),
                () -> assertThat(comment.getContent()).isEqualTo(CommentFixture.CONTENT),
                () -> assertThat(comment.getFeedId().getValue()).isEqualTo(CommentFixture.FEED_ID),
                () -> assertThat(comment.getCreatedAt()).isEqualTo(createdAt),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(createdAt),
                () -> assertThat(comment.isDeleted()).isEqualTo(CommentFixture.DELETED),
                () -> assertThat(comment.getMemberId().getValue()).isEqualTo(CommentFixture.COMMENT_MEMBER_ID)
        );
    }
}
