package com.foodymoody.be.acceptance.comment;

import static com.foodymoody.be.acceptance.comment.CommentSteps._201자인_댓글로_댓글_수정한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.공백인_댓글로_댓글_수정한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.댓글_수정한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.댓글_없이_댓글_수정한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.댓글없이_피드에_댓글_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.비여있는_댓글로_댓글_수정한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.요청_내용_없이_댓글_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_200과_id를_반환한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_200을_반환한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.응답코드_400_검증한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드_아이디_없이_댓글을_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_공백댓글_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에_여러_공백댓글_등록한다;
import static com.foodymoody.be.acceptance.comment.CommentSteps.피드에서_200자_넘는_댓글을_등록한다;
import static com.foodymoody.be.feed.FeedSteps.피드를_등록하고_아이디를_받는다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 관련 기능 인수테스트")
class CommentTest extends AcceptanceTest {

    @Nested
    @DisplayName("댓글 등록 인수테스트")
    class RegisterCommentTest {

        @DisplayName("댓글 등록 요청시 성공하면, 응답코드 200을 응답한다")
        @Test
        void when_register_comment_if_success_then_return_code_200() {
            // docs
            api_문서_타이틀("registerComment_success", spec);

            // given
            String feedId = 피드를_등록하고_아이디를_받는다();

            // when
            var response = 피드에_댓글을_등록한다(feedId, spec);

            // then
            응답코드_200과_id를_반환한다(response);
        }

        @DisplayName("댓글 등록 요청시 요청 바디가 없으면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_request_body_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("registerComment_failed_by_request_body_not_exists", spec);

            // when
            var response = 요청_내용_없이_댓글_등록한다(spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 댓글이 없으면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_content_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("registerComment_failed_by_content_not_exists", spec);

            // given
            String feedId = 피드를_등록하고_아이디를_받는다();

            // when
            var response = 댓글없이_피드에_댓글_등록한다(feedId, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 댓글이 공백이면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_content_is_blank_then_return_code_400() {
            // docs
            api_문서_타이틀("registerComment_failed_by_content_is_empty", spec);

            // given
            String feedId = 피드를_등록하고_아이디를_받는다();

            // when
            var response = 피드에_공백댓글_등록한다(feedId, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 댓글이 space만이면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_content_is_space_then_return_code_400() {
            // docs
            api_문서_타이틀("registerComment_failed_by_content_is_blank", spec);

            // given
            String feedId = 피드를_등록하고_아이디를_받는다();

            // when
            var response = 피드에_여러_공백댓글_등록한다(feedId, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 댓글이 200자를 초과하면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_content_is_larger_than_200_then_return_code_400() {
            // docs
            api_문서_타이틀("registerComment_failed_by_content_is_larger_than_200", spec);

            // given
            String feedId = 피드를_등록하고_아이디를_받는다();

            // when
            var response = 피드에서_200자_넘는_댓글을_등록한다(feedId, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 피드 아이디가 null이면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_feed_id_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("registerComment_failed_by_feed_id_not_exists", spec);

            // when
            var response = 피드_아이디_없이_댓글을_등록한다(spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록시 피디가 존재하지 않으면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_feed_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("registerComment_failed_by_feed_not_exists", spec);

            // given
            String notExistsRegisterId = "notExistsRegisterId";
            String feedId = notExistsRegisterId;

            // when
            var response = 피드에_댓글을_등록한다(feedId, spec);

            // then
            응답코드_400_검증한다(response);
        }
    }

    @Nested
    @DisplayName("댓글 수정 인스테스트")
    class UpdateCommentTest {

        String feedId;
        String memberId;

        @BeforeEach
        void setUp() {
            feedId = 피드를_등록하고_아이디를_받는다();
            memberId = 피드에_댓글을_등록하고_아이디를_받는다(feedId);
        }

        @DisplayName("댓글 수정 요청시 성공하면, 응답코드 200을 응답한다")
        @Test
        void when_edit_comment_if_success_then_return_code_200() {
            // docs
            api_문서_타이틀("editComment_success", spec);

            // when
            var response = 댓글_수정한다(memberId, spec);

            // then
            응답코드_200을_반환한다(response);
        }

        @DisplayName("댓글 수정 요청시 요청 바디가 없으면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_request_body_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("editComment_failed_by_request_body_not_exists", spec);

            // when
            var response = 댓글_없이_댓글_수정한다(spec, memberId);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 수정 요청시 댓글이 비여있으면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_content_is_empty_then_return_code_400() {
            // docs
            api_문서_타이틀("editComment_failed_by_content_is_empty", spec);

            // when
            var response = 비여있는_댓글로_댓글_수정한다(spec, memberId);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 수정 요청시 댓글이 공백이면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_content_is_space_then_return_code_400() {
            // docs
            api_문서_타이틀("editComment_failed_by_content_is_blank", spec);

            // when
            var response = 공백인_댓글로_댓글_수정한다(spec, memberId);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 수정 요청시 댓글이 200자를 초과하면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_content_is_larger_than_200_return_code_400() {
            // docs
            api_문서_타이틀("editComment_failed_by_content_is_larger_than_200", spec);

            // when
            var response = _201자인_댓글로_댓글_수정한다(spec, memberId);

            // then
            응답코드_400_검증한다(response);
        }
    }
}
