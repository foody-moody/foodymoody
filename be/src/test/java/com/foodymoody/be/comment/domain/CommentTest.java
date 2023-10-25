package com.foodymoody.be.comment.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.util.CommentFixture;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    @DisplayName("댓글을 수정한다")
    @Test
    void edit() {
        // given
        Comment comment = CommentFixture.comment();
        LocalDateTime updatedAt = CommentFixture.newUpdatedAt();

        // when
        comment.edit(CommentFixture.NEW_CONTENT, updatedAt);

        // then
        assertThat(comment.getContent()).isEqualTo(CommentFixture.NEW_CONTENT);
        assertThat(comment.getUpdatedAt()).isEqualTo(CommentFixture.newUpdatedAt());
    }
}
