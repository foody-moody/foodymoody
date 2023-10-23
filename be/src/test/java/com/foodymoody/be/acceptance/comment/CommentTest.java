package com.foodymoody.be.acceptance.comment;

import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_200_검증한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록한다;
import static com.foodymoody.be.feed.FeedSteps.피드를_등록하고_아이디를_받는다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest extends AcceptanceTest {


    @DisplayName("댓글 등록 요청시 성공하면, 응답코드 200을 응답한다")
    @Test
    void when_register_comment_if_success_return_code_200() {
        // docs
        api_문서_타이틀("registerComment", spec);

        // given
        long feedId = 피드를_등록하고_아이디를_받는다();

        // when
        var response = 피드에_댓글을_등록한다(feedId, spec);

        // then
        응답코드_200_검증한다(response);
    }
}
