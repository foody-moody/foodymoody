package com.foodymoody.be.acceptance.comment;

import static com.foodymoody.be.acceptance.comment.CommentSteps.댓글없이_피드에_댓글_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_200_검증한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_400_검증한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_공백댓글_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록한다;
import static com.foodymoody.be.feed.FeedSteps.피드를_등록하고_아이디를_받는다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest extends AcceptanceTest {


    @DisplayName("댓글 등록 요청시 성공하면, 응답코드 200을 응답한다")
    @Test
    void when_register_comment_if_success_then_return_code_200() {
        // docs
        api_문서_타이틀("registerComment_success", spec);

        // given
        long feedId = 피드를_등록하고_아이디를_받는다();

        // when
        var response = 피드에_댓글을_등록한다(feedId, spec);

        // then
        응답코드_200_검증한다(response);
    }

    @DisplayName("댓글 등록 요청시 댓글이 없으면 응답코드 400을 응답한다")
    @Test
    void when_register_comment_if_content_not_exists_then_return_code_400() {
        // docs
        api_문서_타이틀("registerComment_failed_by_content_not_exists", spec);

        // given
        long feedId = 피드를_등록하고_아이디를_받는다();

        // when
        var response = 댓글없이_피드에_댓글_등록한다(feedId, spec);

        // then
        응답코드_400_검증한다(response);
    }

    @DisplayName("댓글 등록 요청시 댓글이 공백이면 응답코드 400을 응답한다")
    @Test
    void when_register_comment_if_content_is_blank_then_return_code_400() {
        // docs
        api_문서_타이틀("registerComment_failed_by_content_is_blank", spec);

        // given
        long feedId = 피드를_등록하고_아이디를_받는다();

        // when
        var response = 피드에_공백댓글_등록한다(feedId, spec);

        // then
        응답코드_400_검증한다(response);
    }
}
