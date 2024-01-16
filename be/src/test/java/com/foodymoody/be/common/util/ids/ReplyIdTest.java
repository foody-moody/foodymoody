package com.foodymoody.be.common.util.ids;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReplyIdTest {

    @DisplayName("대댓글 아이디는 값이 같고 타입도 같으면 같은 객체로 취급한다")
    @Test
    void if_same_value_and_reply_id() {
        // given
        ReplyId replyId1 = new ReplyId("1");
        ReplyId replyId2 = new ReplyId("1");

        // when
        boolean result = replyId1.equals(replyId2);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("대댓글 아이디는 값이 다르면 다른 객체로 취급한다")
    @Test
    void if_different_value_and_reply_id() {
        // given
        ReplyId replyId1 = new ReplyId("1");
        ReplyId replyId2 = new ReplyId("2");

        // when
        boolean result = replyId1.equals(replyId2);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("대댓글 아이디는 같은데 타입이 다르면 다른 객체로 취급한다")
    @Test
    void if_same_value_but_different_type_and_reply_id() {
        // given
        ReplyId replyId1 = new ReplyId("1");
        CommentId commentId = new CommentId("1");

        // when
        boolean result = replyId1.equals(commentId);

        // then
        assertThat(result).isFalse();
    }
}
