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
        var commentMapper = new CommentMapper();
        var createdAt = CommentFixture.CREATED_AT;
        var commentId = new CommentId(CommentFixture.COMMENT_ID);
        var data = CommentFixture.registerCommentData();

        // when
        Comment comment = commentMapper.toEntity(
                data,
                commentId,
                createdAt
        );

        // then
        assertThat(comment).isNotNull();
        Assertions.assertAll(
                () -> assertThat(comment.getId()).usingRecursiveComparison().isEqualTo(commentId),
                () -> assertThat(comment.getContent()).usingRecursiveComparison().isEqualTo(data.getContent()),
                () -> assertThat(comment.getFeedId()).usingRecursiveComparison().isEqualTo(data.getFeedId()),
                () -> assertThat(comment.getCreatedAt()).isEqualTo(createdAt),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(createdAt),
                () -> assertThat(comment.isDeleted()).isEqualTo(CommentFixture.DELETED),
                () -> assertThat(comment.getMemberId()).usingRecursiveComparison().isEqualTo(data.getMemberId())
        );
    }
}
