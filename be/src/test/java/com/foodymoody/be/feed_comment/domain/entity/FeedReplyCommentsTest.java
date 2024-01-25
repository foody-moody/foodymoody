package com.foodymoody.be.feed_comment.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.feed_comment.util.FeedCommentFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("FeedReplyComments 테스트")
class FeedReplyCommentsTest {

    @DisplayName("댓글 추가")
    @Test
    void add() {
        // given
        FeedReplyComments feedReplyComments = new FeedReplyComments();
        FeedReply feedReply = FeedCommentFixture.reply();

        // when
        feedReplyComments.add(feedReply);

        // then
        assertThat(feedReplyComments.getCommentList()).containsOnly(feedReply);
    }

    @DisplayName("댓글 목록 조회")
    @Test
    void getCommentList() {
        // given
        FeedReplyComments feedReplyComments = new FeedReplyComments();
        FeedReply feedReply = FeedCommentFixture.reply();
        feedReplyComments.add(feedReply);

        // when
        var commentList = feedReplyComments.getCommentList();

        // then
        assertThat(commentList).containsOnly(feedReply);
    }

    @DisplayName("댓글 삭제")
    @Test
    void delete() {
        // given
        FeedReplyComments feedReplyComments = new FeedReplyComments();
        FeedReply feedReply = FeedCommentFixture.reply();
        feedReplyComments.add(feedReply);

        // when
        feedReplyComments.delete(feedReply);

        // then
        assertThat(feedReplyComments.getCommentList()).isEmpty();
    }
}
