package com.foodymoody.be.comment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.util.CommentFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    @DisplayName("댓글을 수정한다")
    @Test
    void edit() {
        // given
        Comment comment = CommentFixture.comment();

        // when
        comment.edit(CommentFixture.NEW_CONTENT);

        // then
        assertThat(comment.getContent()).isEqualTo(CommentFixture.NEW_CONTENT);
    }
}
