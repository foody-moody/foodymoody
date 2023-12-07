package com.foodymoody.be.acceptance.reply_heart;

import static com.foodymoody.be.acceptance.comment.CommentSteps.댓글에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.댓글의_댓글을_조회한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.reply_heart.ReplyHeartSteps.좋아요를_누린다;
import static com.foodymoody.be.acceptance.reply_heart.ReplyHeartSteps.좋아요를_취소한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글의 댓글 좋아요 인수 테스트")
class ReplyFeedHeartAcceptanceTest extends AcceptanceTest {

    String feedId;
    String commentId;
    String firstReplyId;

    @BeforeEach
    void setUp() {
        feedId = 피드를_등록하고_아이디를_받는다(회원푸반_액세스토큰);
        commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);
        댓글에_댓글을_등록한다(feedId, commentId, 회원푸반_액세스토큰);
        firstReplyId = 댓글의_댓글을_조회한다(commentId).jsonPath().getList("content.id", String.class).get(0);
    }

    @DisplayName("댓글의 댓글 좋아요를 누르면 좋아요가 반영된다.")
    @Test
    void createReplyHeart() {
        // docs
        api_문서_타이틀("reply_heart_register_success", spec);

        // when
        var response = 좋아요를_누린다(회원푸반_액세스토큰, commentId, firstReplyId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(201);
    }

    @DisplayName("댓글의 댓글 좋아요를 취소하면 좋아요가 취소된다.")
    @Test
    void deleteReplyHeart() {
        // docs
        api_문서_타이틀("reply_heart_cancel_success", spec);

        // given
        좋아요를_누린다(회원푸반_액세스토큰, commentId, firstReplyId);

        // when
        var response = 좋아요를_취소한다(회원푸반_액세스토큰, commentId, firstReplyId, spec);

        // then
        assertThat(response.statusCode()).isEqualTo(204);
    }
}
