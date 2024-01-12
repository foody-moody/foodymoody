package com.foodymoody.be.comment.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.util.CommentFixture;
import com.foodymoody.be.common.util.ids.IdFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReplyTest {

    @DisplayName("대댓글은 아이디만 같으면 같은 객체로 취급한다")
    @Test
    void if_id_is_same_then_reply_is_same() {
        // given
        var replyId = IdFactory.createReplyId();
        var memberId = CommentFixture.memberId();
        var createdAt = CommentFixture.newLocalTime();
        var updatedAt = CommentFixture.newLocalTime();
        var reply = new Reply(replyId, CommentFixture.content(), false, memberId, createdAt, updatedAt);

        var otherMemberId = CommentFixture.newMemberId();
        var otherCreatedAt = CommentFixture.newLocalTime();
        var otherUpdatedAt = CommentFixture.newLocalTime();
        var sameIdReply = new Reply(
                replyId, CommentFixture.content(), false, otherMemberId, otherCreatedAt, otherUpdatedAt);

        // when ,then
        assertThat(reply).isEqualTo(sameIdReply);
    }
}
