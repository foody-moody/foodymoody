package com.foodymoody.be.feed_comment.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.PermissionDeniedAccessException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_comment.util.FeedCommentFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedReplyTest {

    @DisplayName("대댓글은 아이디만 같으면 같은 객체로 취급한다")
    @Test
    void if_id_is_same_then_reply_is_same() {
        // given
        var replyId = IdFactory.createFeedReplyId();
        var memberId = FeedCommentFixture.memberId();
        var createdAt = FeedCommentFixture.newLocalTime();
        var updatedAt = FeedCommentFixture.newLocalTime();
        var reply = new FeedReply(replyId, FeedCommentFixture.content(), false, memberId, createdAt, updatedAt);

        var otherMemberId = FeedCommentFixture.newMemberId();
        var otherCreatedAt = FeedCommentFixture.newLocalTime();
        var otherUpdatedAt = FeedCommentFixture.newLocalTime();
        var sameIdReply = new FeedReply(
                replyId, FeedCommentFixture.content(), false, otherMemberId, otherCreatedAt, otherUpdatedAt);

        // when ,then
        assertThat(reply).isEqualTo(sameIdReply);
    }

    @DisplayName("멤버 아이디가 일치 할 때 대댓글을 수정한다")
    @Test
    void when_edit_if_member_id_is_same_then_update_reply() {
        // given
        var replyId = IdFactory.createFeedReplyId();
        var memberId = FeedCommentFixture.memberId();
        var createdAt = FeedCommentFixture.newLocalTime();
        var updatedAt = FeedCommentFixture.newLocalTime();
        var reply = new FeedReply(replyId, FeedCommentFixture.content(), false, memberId, createdAt, updatedAt);

        var newContent = FeedCommentFixture.newContent();
        var newUpdatedAt = FeedCommentFixture.newLocalTime();

        // when
        reply.update(memberId, newContent, newUpdatedAt);

        // then
        assertThat(reply.getContent()).isEqualTo(newContent);
        assertThat(reply.getUpdatedAt()).isEqualTo(newUpdatedAt);
    }

    @DisplayName("멤버 아이디가 일치하지 않으면 예외를 던진다")
    @Test
    void when_edit_if_member_id_is_not_same_then_throw_exception() {
        // given
        var replyId = IdFactory.createFeedReplyId();
        var memberId = FeedCommentFixture.memberId();
        var createdAt = FeedCommentFixture.newLocalTime();
        var updatedAt = FeedCommentFixture.newLocalTime();
        var reply = new FeedReply(replyId, FeedCommentFixture.content(), false, memberId, createdAt, updatedAt);

        var otherMemberId = FeedCommentFixture.newMemberId();
        var newContent = FeedCommentFixture.newContent();
        var newUpdatedAt = FeedCommentFixture.newLocalTime();

        // when, then
        assertThatThrownBy(() -> reply.update(otherMemberId, newContent, newUpdatedAt))
                .isInstanceOf(PermissionDeniedAccessException.class);
    }

    @DisplayName("멤버 아이디가 일치 할 때 대댓글을 삭제한다")
    @Test
    void when_delete_if_member_id_is_same_then_delete_reply() {
        // given
        var replyId = IdFactory.createFeedReplyId();
        var memberId = FeedCommentFixture.memberId();
        var createdAt = FeedCommentFixture.newLocalTime();
        var updatedAt = FeedCommentFixture.newLocalTime();
        var reply = new FeedReply(replyId, FeedCommentFixture.content(), false, memberId, createdAt, updatedAt);

        var newUpdatedAt = FeedCommentFixture.newLocalTime();

        // when
        reply.delete(memberId, newUpdatedAt);

        // then
        assertThat(reply.isDeleted()).isTrue();
        assertThat(reply.getUpdatedAt()).isEqualTo(newUpdatedAt);
    }

    @DisplayName("멤버 아이디가 일치하지 않으면 예외를 던진다")
    @Test
    void when_delete_if_member_id_is_not_same_then_throw_exception() {
        // given
        var replyId = IdFactory.createFeedReplyId();
        var memberId = FeedCommentFixture.memberId();
        var createdAt = FeedCommentFixture.newLocalTime();
        var updatedAt = FeedCommentFixture.newLocalTime();
        var reply = new FeedReply(replyId, FeedCommentFixture.content(), false, memberId, createdAt, updatedAt);

        var otherMemberId = FeedCommentFixture.newMemberId();
        var newUpdatedAt = FeedCommentFixture.newLocalTime();

        // when, then
        assertThatThrownBy(() -> reply.delete(otherMemberId, newUpdatedAt))
                .isInstanceOf(PermissionDeniedAccessException.class);
    }

}
