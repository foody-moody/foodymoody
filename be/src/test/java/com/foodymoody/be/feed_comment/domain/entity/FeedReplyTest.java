package com.foodymoody.be.feed_comment.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

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
}
