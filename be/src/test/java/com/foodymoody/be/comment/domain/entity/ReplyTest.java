package com.foodymoody.be.comment.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.util.CommentFixture;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ReplyId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReplyTest {

    @DisplayName("대댓글은 아이디만 같으면 같은 객체로 취급한다")
    @Test
    void if_id_is_same_then_reply_is_same() {
        // given
        ReplyId replyId = IdFactory.createReplyId();
        Reply reply = new Reply(replyId, CommentFixture.content(), false, null, null, null);
        Reply sameIdReply = new Reply(replyId, CommentFixture.content(), false, null, null, null);

        // when ,then
        assertThat(reply).isEqualTo(sameIdReply);
    }
}
