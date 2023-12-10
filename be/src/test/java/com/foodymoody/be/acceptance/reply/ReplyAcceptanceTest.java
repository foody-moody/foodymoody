package com.foodymoody.be.acceptance.reply;

import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_200을_반환한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_201을_반환한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.reply.ReplySteps.댓글에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.reply.ReplySteps.댓글의_댓글을_조회한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReplyAcceptanceTest extends AcceptanceTest {

    String feedId;
    String commentId;

    @BeforeEach
    void setUp() {
        feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰);
        commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);

    }

    @DisplayName("댓글에 댓글 추가하면 응답코드 201을 응답한다")
    @Test
    void when_register_comment_to_comment_if_success_then_return_code_201() {
        // docs
        api_문서_타이틀("comment_reply_success", spec);

        // when
        var response = 댓글에_댓글을_등록한다(feedId, commentId, 회원아티_액세스토큰, spec);

        // then
        응답코드_201을_반환한다(response);
    }

    @DisplayName("댓글의 댓글을 조회하면 응답코드 200을 응답한다")
    @Test
    void when_fetch_comment_of_comment_if_success_then_return_code_200() {
        // docs
        api_문서_타이틀("comment_reply_fetch_success", spec);

        // given
        댓글에_댓글을_등록한다(feedId, commentId, 회원아티_액세스토큰);
        댓글에_댓글을_등록한다(feedId, commentId, 회원푸반_액세스토큰);
        댓글에_댓글을_등록한다(feedId, commentId, 회원아티_액세스토큰);

        // when
        var response = 댓글의_댓글을_조회한다(commentId, spec);

        // then
        응답코드_200을_반환한다(response);
    }
}