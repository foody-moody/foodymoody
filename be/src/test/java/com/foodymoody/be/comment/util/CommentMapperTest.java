package com.foodymoody.be.comment.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.controller.dto.CommentResponse;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import com.foodymoody.be.comment.service.CommentMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 매퍼 테스트")
class CommentMapperTest {

    @DisplayName("toEntity() 메소드는 RegisterCommentRequest 객체와 id를 인자로 받아 Comment 객체를 반환한다.")
    @Test
    void toEntity() {
        // given
        RegisterCommentRequest request = CommentFixture.registerCommentRequest();
        CommentMapper commentMapper = new CommentMapper();

        // when
        Comment comment = commentMapper.toEntity(request, CommentFixture.CREATED_AT,
                new CommentId(CommentFixture.COMMENT_ID));

        // then
        Assertions.assertAll(
                () -> assertThat(comment).isNotNull(),
                () -> assertThat(comment.getId()).usingRecursiveComparison().isEqualTo(CommentFixture.commentId()),
                () -> assertThat(comment.getContent()).isEqualTo(CommentFixture.CONTENT),
                () -> assertThat(comment.getFeedId()).isEqualTo(CommentFixture.FEED_ID),
                () -> assertThat(comment.getCreatedAt()).isEqualTo(CommentFixture.CREATED_AT),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(CommentFixture.CREATED_AT)
        );
    }

    @DisplayName("toResponse() 메소드는 Comment 객체를 인자로 받아 CommentResponse 객체를 반환한다.")
    @Test
    void toResponse() {
        // given
        Comment comment = CommentFixture.comment();
        CommentMapper commentMapper = new CommentMapper();

        // when
        CommentResponse commentResponse = commentMapper.toResponse(comment);

        // then
        Assertions.assertAll(
                () -> assertThat(commentResponse).isNotNull(),
                () -> assertThat(commentResponse.getId()).isEqualTo(CommentFixture.COMMENT_ID),
                () -> assertThat(commentResponse.getContent()).isEqualTo(CommentFixture.CONTENT),
                () -> assertThat(commentResponse.isDeleted()).isEqualTo(CommentFixture.DELETED),
                () -> assertThat(commentResponse.getCreatedAt()).isEqualTo(CommentFixture.CREATED_AT),
                () -> assertThat(commentResponse.getUpdatedAt()).isEqualTo(CommentFixture.CREATED_AT)
        );
    }
}
