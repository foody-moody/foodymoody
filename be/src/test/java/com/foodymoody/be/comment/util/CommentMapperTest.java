package com.foodymoody.be.comment.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
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

        // when
        Comment comment = CommentMapper.toEntity(request, CommentFixture.COMMENT_ID, CommentFixture.CREATED_AT);

        // then
        Assertions.assertAll(
                () -> assertThat(comment).isNotNull(),
                () -> assertThat(comment.getId()).isEqualTo(CommentFixture.COMMENT_ID),
                () -> assertThat(comment.getContent()).isEqualTo(CommentFixture.CONTENT),
                () -> assertThat(comment.getFeedId()).isEqualTo(CommentFixture.FEED_ID),
                () -> assertThat(comment.getCreatedAt()).isEqualTo(CommentFixture.CREATED_AT),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(CommentFixture.CREATED_AT)
        );
    }
}
