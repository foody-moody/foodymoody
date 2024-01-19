package com.foodymoody.be.common.util.ids;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedFeedReplyIdTest {

    @DisplayName("대댓글 아이디는 값이 같고 타입도 같으면 같은 객체로 취급한다")
    @Test
    void if_same_value_and_reply_id() {
        // given
        FeedReplyId feedReplyId1 = new FeedReplyId("1");
        FeedReplyId feedReplyId2 = new FeedReplyId("1");

        // when
        boolean result = feedReplyId1.equals(feedReplyId2);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("대댓글 아이디는 값이 다르면 다른 객체로 취급한다")
    @Test
    void if_different_value_and_reply_id() {
        // given
        FeedReplyId feedReplyId1 = new FeedReplyId("1");
        FeedReplyId feedReplyId2 = new FeedReplyId("2");

        // when
        boolean result = feedReplyId1.equals(feedReplyId2);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("대댓글 아이디는 같은데 타입이 다르면 다른 객체로 취급한다")
    @Test
    void if_same_value_but_different_type_and_reply_id() {
        // given
        FeedReplyId feedReplyId1 = new FeedReplyId("1");
        FeedCommentId feedCommentId = new FeedCommentId("1");

        // when
        boolean result = feedReplyId1.equals(feedCommentId);

        // then
        assertThat(result).isFalse();
    }
}
