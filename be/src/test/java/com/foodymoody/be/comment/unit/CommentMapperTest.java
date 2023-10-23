package com.foodymoody.be.comment.unit;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentMapperTest {

    @DisplayName("toEntity() 메소드는 RegisterCommentRequest 객체와 id를 인자로 받아 Comment 객체를 반환한다.")
    @Test
    void toEntity() {
        // given
        long feedId = 1L;
        String content = "content";
        RegisterCommentRequest request = new RegisterCommentRequest(feedId, content);
        long commentId = 1L;

        // when
        Comment comment = CommentMapper.toEntity(request, commentId);

        // then
        Assertions.assertAll(
                () -> assertThat(comment).isNotNull(),
                () -> assertThat(comment.getId()).isEqualTo(commentId),
                () -> assertThat(comment.getContent()).isEqualTo(content),
                () -> assertThat(comment.getFeedId()).isEqualTo(feedId)
        );
    }
}
