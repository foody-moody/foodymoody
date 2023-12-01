package com.foodymoody.be.acceptance.sse;

import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.sse.SseSteps.sse_연결_요청;
import static com.foodymoody.be.acceptance.sse.SseSteps.응답코드가_200;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SseAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    void setUp() {
        var feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
        피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원푸반_액세스토큰);
    }

    @DisplayName("SSE 요청 성공하면 응답코드 200과 SSE를 받는다.")
    @Test
    void when_request_sse_if_success_return_200_and_receive_sse() {
        // docs
        api_문서_타이틀("sse_request_success", spec);

        // when
        var response = sse_연결_요청(spec, 회원아티_액세스토큰);

        // then
        응답코드가_200(response);
    }
}
