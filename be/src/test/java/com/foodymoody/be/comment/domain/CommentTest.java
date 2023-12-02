package com.foodymoody.be.comment.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.util.CommentFixture;
import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.exception.CommentDeletedException;
import com.foodymoody.be.common.exception.ErrorMessage;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    @BeforeEach
    void setUp() {
        Events.setPublisher(event -> {
        });
    }


    @DisplayName("댓글을 수정하면 댓글 내용이 변경되고 수정된 시간이 저장된다")
    @Test
    void edit_success() {
        // given
        Comment comment = CommentFixture.comment();
        LocalDateTime updatedAt = CommentFixture.newUpdatedAt();

        // when
        comment.edit(CommentFixture.MEMBER_ID, CommentFixture.NEW_CONTENT, updatedAt);

        // then
        assertAll(
                () -> assertThat(comment.getContent()).isEqualTo(CommentFixture.NEW_CONTENT),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(updatedAt),
                () -> assertThat(comment.getUpdatedAt()).isNotEqualTo(comment.getCreatedAt())
        );
    }

    @DisplayName("댓글을 수정할 때  댓글이 삭제되어 있으면 CommentDeletedException이 발생한다")
    @Test
    void edit_fail_if_comment_is_deleted() {
        // given
        Comment comment = CommentFixture.deletedComment();
        LocalDateTime updatedAt = CommentFixture.newUpdatedAt();

        // when
        Assertions.assertThatThrownBy(
                        () -> comment.edit(CommentFixture.MEMBER_ID, CommentFixture.NEW_CONTENT, updatedAt))
                .isInstanceOf(CommentDeletedException.class)
                .message().isEqualTo(ErrorMessage.COMMENT_DELETED.getMessage());
    }

    @DisplayName("댓글을 수정할 때 댓글 작성자가 아니면 IllegalArgumentException이 발생한다")
    @Test
    void edit_fail_if_not_comment_writer() {
        // given
        Comment comment = CommentFixture.comment();
        LocalDateTime updatedAt = CommentFixture.newUpdatedAt();

        // when
        Assertions.assertThatThrownBy(
                        () -> comment.edit(CommentFixture.NOT_MEMBER_ID, CommentFixture.NEW_CONTENT, updatedAt))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("댓글을 삭제 하면 댓글 삭제 여북 ture이고 삭제된 시간이 저장된다")
    @Test
    void delete_success() {
        // given
        Comment comment = CommentFixture.comment();
        LocalDateTime localDateTime = CommentFixture.newUpdatedAt();

        // when
        comment.delete(CommentFixture.MEMBER_ID, localDateTime);

        // then
        assertAll(
                () -> assertThat(comment.isDeleted()).isTrue(),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(localDateTime)
        );

    }

    @DisplayName("댓글을 삭제할 때 이미 삭제되어 있으면 CommentDeletedException이 발생한다")
    @Test
    void delete_fail_if_comment_is_deleted() {
        // given
        Comment comment = CommentFixture.deletedComment();
        LocalDateTime localDateTime = CommentFixture.newUpdatedAt();

        // when
        Assertions.assertThatThrownBy(() -> comment.delete(CommentFixture.MEMBER_ID, localDateTime))
                .isInstanceOf(CommentDeletedException.class)
                .message().isEqualTo(ErrorMessage.COMMENT_DELETED.getMessage());
    }

    @DisplayName("댓글을 삭제할 때 댓글 작성자가 아니면 IllegalArgumentException이 발생한다")
    @Test
    void delete_fail_if_not_comment_writer() {
        // given
        Comment comment = CommentFixture.comment();
        LocalDateTime localDateTime = CommentFixture.newUpdatedAt();

        // when
        Assertions.assertThatThrownBy(() -> comment.delete(CommentFixture.NOT_MEMBER_ID, localDateTime))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
