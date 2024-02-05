package com.foodymoody.be.feed_comment.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.foodymoody.be.common.event.EventManager;
import com.foodymoody.be.common.exception.CommentDeletedException;
import com.foodymoody.be.common.exception.ErrorMessage;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentAddedEvent;
import com.foodymoody.be.feed_comment.domain.entity.FeedCommentReplyAddedEvent;
import com.foodymoody.be.feed_comment.util.FeedCommentFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 도메인")
class FeedCommentTest {

    @DisplayName("댓글을 수정하면 댓글 내용이 변경되고 수정된 시간이 저장된다")
    @Test
    void edit_success() {
        // given
        var comment = FeedCommentFixture.comment();
        var updatedAt = FeedCommentFixture.newUpdatedAt();

        // when
        comment.edit(FeedCommentFixture.memberId(), FeedCommentFixture.newContent(), updatedAt);

        // then
        assertAll(
                () -> assertThat(comment.getContent()).usingRecursiveComparison()
                        .isEqualTo(FeedCommentFixture.newContent()),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(updatedAt),
                () -> assertThat(comment.getUpdatedAt()).isNotEqualTo(comment.getCreatedAt())
        );
    }

    @DisplayName("댓글을 수정할 때  댓글이 삭제되어 있으면 CommentDeletedException이 발생한다")
    @Test
    void edit_fail_if_comment_is_deleted() {
        // given
        var comment = FeedCommentFixture.deletedComment();
        var updatedAt = FeedCommentFixture.newUpdatedAt();
        var memberId = FeedCommentFixture.memberId();
        var content = FeedCommentFixture.newContent();

        // when
        assertThatThrownBy(
                () -> comment.edit(memberId, content, updatedAt))
                .isInstanceOf(CommentDeletedException.class)
                .message().isEqualTo(ErrorMessage.COMMENT_DELETED.getMessage());
    }

    @DisplayName("댓글을 수정할 때 댓글 작성자가 아니면 IllegalArgumentException이 발생한다")
    @Test
    void edit_fail_if_not_comment_writer() {
        // given
        var comment = FeedCommentFixture.comment();
        var updatedAt = FeedCommentFixture.newUpdatedAt();
        var memberId = FeedCommentFixture.notExistsMemberId();
        var content = FeedCommentFixture.newContent();

        // when
        assertThatThrownBy(
                () -> comment.edit(memberId, content, updatedAt))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("댓글을 삭제 하면 댓글 삭제 여북 ture이고 삭제된 시간이 저장된다")
    @Test
    void delete_success() {
        // given
        var comment = FeedCommentFixture.comment();
        var localDateTime = FeedCommentFixture.newUpdatedAt();

        // when
        comment.delete(FeedCommentFixture.memberId(), localDateTime);

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
        var comment = FeedCommentFixture.deletedComment();
        var localDateTime = FeedCommentFixture.newUpdatedAt();
        var memberId = FeedCommentFixture.memberId();

        // when
        assertThatThrownBy(() -> comment.delete(memberId, localDateTime))
                .isInstanceOf(CommentDeletedException.class)
                .message().isEqualTo(ErrorMessage.COMMENT_DELETED.getMessage());
    }

    @DisplayName("댓글을 삭제할 때 댓글 작성자가 아니면 IllegalArgumentException이 발생한다")
    @Test
    void delete_fail_if_not_comment_writer() {
        // given
        var comment = FeedCommentFixture.comment();
        var localDateTime = FeedCommentFixture.newUpdatedAt();
        var memberId = FeedCommentFixture.notExistsMemberId();

        // when
        assertThatThrownBy(() -> comment.delete(memberId, localDateTime))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("댓글에 답글을 추가하면 답글이 추가되고 답글이 있는지 여부가 true로 변경하고 수정된 시간이 저장된다.그리고 대댓글 추가 이벤트 발생한다")
    @Test
    void add_reply_success() {
        // given
        var comment = FeedCommentFixture.comment();
        var updatedAt = FeedCommentFixture.newUpdatedAt();
        EventManager.clear();

        // when
        var reply = FeedCommentFixture.reply();
        comment.addReply(reply, updatedAt);

        // then
        var commentList = comment.getFeedReplyComments().getCommentList();
        var event = EventManager.getEvents().get(0);
        assertThat(event).isInstanceOf(FeedCommentReplyAddedEvent.class);
        var commentRepliedAddedEvent = (FeedCommentReplyAddedEvent) event;
        assertAll(
                () -> assertThat(comment.isHasReply()).isTrue(),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(updatedAt),
                () -> assertThat(commentList).containsOnly(reply),
                () -> assertThat(commentRepliedAddedEvent.getFeedCommentId()).isEqualTo(comment.getId()),
                () -> assertThat(commentRepliedAddedEvent.getFeedReplyId()).isEqualTo(reply.getId()),
                () -> assertThat(commentRepliedAddedEvent.getReplyContent()).isEqualTo(reply.getContent()),
                () -> assertThat(commentRepliedAddedEvent.getCreatedAt()).isEqualTo(reply.getCreatedAt()),
                () -> assertThat(commentRepliedAddedEvent.getToMemberId()).isEqualTo(comment.getMemberId()),
                () -> assertThat(commentRepliedAddedEvent.getFeedId()).isEqualTo(comment.getFeedId()),
                () -> assertThat(commentRepliedAddedEvent.getFromMemberId()).isEqualTo(reply.getMemberId())
        );
    }

    @DisplayName("댓글이 생성되면 댓글 추가 이벤트가 발생한다")
    @Test
    void raise_comment_added_event() {
        // given
        EventManager.clear();

        // when
        var comment = FeedCommentFixture.comment();

        // then
        var event = EventManager.getEvents().get(0);
        assertThat(event).isInstanceOf(FeedCommentAddedEvent.class);
        var commentAddedEvent = (FeedCommentAddedEvent) event;
        assertAll(
                () -> assertThat(commentAddedEvent.getFeedCommentId()).isEqualTo(comment.getId()),
                () -> assertThat(commentAddedEvent.getCommentContent()).isEqualTo(comment.getContent()),
                () -> assertThat(commentAddedEvent.getCreatedAt()).isEqualTo(comment.getCreatedAt()),
                () -> assertThat(commentAddedEvent.getFeedId()).isEqualTo(comment.getFeedId()),
                () -> assertThat(commentAddedEvent.getCreatedAt()).isEqualTo(comment.getCreatedAt())
        );
    }

    @DisplayName("댓글에 답글을 삭제하면 답글이 삭제되고 수정된 시간이 저장된다")
    @Test
    void delete_reply_success() {
        // given
        var comment = FeedCommentFixture.comment();
        var updatedAt = FeedCommentFixture.newUpdatedAt();
        var reply = FeedCommentFixture.reply();
        comment.addReply(reply, updatedAt);

        // when
        comment.deleteReply(reply, updatedAt);

        // then
        var commentList = comment.getFeedReplyComments().getCommentList();
        assertAll(
                () -> assertThat(commentList).isEmpty(),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(updatedAt)
        );
    }
}
