package com.foodymoody.be.acceptance.feed_comment;

import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps._201자인_댓글로_댓글_수정한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.공백인_댓글로_댓글_수정한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.댓글_수정한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.댓글_없이_댓글_수정한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.댓글없이_피드에_댓글_등록한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.댓글을_삭제한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.비여있는_댓글로_댓글_수정한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.요청_내용_없이_댓글_등록한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.응답코드_200을_반환한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.응답코드_201과_id를_반환한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.응답코드_400_검증한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.페이지_적용_조회_검증;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.페이지_적용_피드별_댓글을_조회한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.피드별_댓글을_조회한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.피드에_공백댓글_등록한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.피드에_댓글을_등록하고_아이디를_받는다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.피드에_댓글을_등록한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.피드에_여러_공백댓글_등록한다;
import static com.foodymoody.be.acceptance.feed_comment.FeedCommentSteps.피드에서_200자_넘는_댓글을_등록한다;
import static com.foodymoody.be.acceptance.feed_comment_like.FeedCommentLikeSteps.댓글에_좋아요를_누른다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 관련 기능 인수테스트")
class FeedCommentAcceptanceTest extends AcceptanceTest {

    @Nested
    @DisplayName("댓글 등록 인수테스트")
    class RegisterFeedCommentTest {

        String feedId;

        @BeforeEach
        void setFeedId() {
            var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
            String id1 = imageResponse1.jsonPath().getString("id");
            var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
            String id2 = imageResponse2.jsonPath().getString("id");
            List<String> imageIds = List.of(id1, id2);

            feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds);
        }

        @DisplayName("댓글 등록 요청시 성공하면, 응답코드 200을 응답한다")
        @Test
        void when_register_comment_if_success_then_return_code_200() {
            // docs
            api_문서_타이틀("comment_register_success", spec);

            // when
            var response = 피드에_댓글을_등록한다(feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_201과_id를_반환한다(response);
        }

        @DisplayName("댓글 등록 요청시 요청 바디가 없으면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_request_body_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_register_failed_by_request_body_not_exists", spec);

            // when
            var response = 요청_내용_없이_댓글_등록한다(feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 댓글이 없으면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_content_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_register_failed_by_content_not_exists", spec);

            // when
            var response = 댓글없이_피드에_댓글_등록한다(feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 댓글이 공백이면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_content_is_blank_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_register_failed_by_content_is_empty", spec);

            // when
            var response = 피드에_공백댓글_등록한다(feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 댓글이 space만이면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_content_is_space_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_register_failed_by_content_is_blank", spec);

            // when
            var response = 피드에_여러_공백댓글_등록한다(feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록 요청시 댓글이 200자를 초과하면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_content_is_larger_than_200_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_register_failed_by_content_is_larger_than_200", spec);

            // when
            var response = 피드에서_200자_넘는_댓글을_등록한다(feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 등록시 피디가 존재하지 않으면 응답코드 400을 응답한다")
        @Test
        void when_register_comment_if_feed_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_register_failed_by_feed_not_exists", spec);

            // given
            String notExistsRegisterId = "notExistsRegisterId";
            String feedId = notExistsRegisterId;

            // when
            var response = 피드에_댓글을_등록한다(feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

    }

    @Nested
    @DisplayName("댓글 수정 인스테스트")
    class UpdateFeedCommentTest {

        String feedId;
        String commentId;

        @BeforeEach
        void setUp() {
            var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
            String id1 = imageResponse1.jsonPath().getString("id");
            var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
            String id2 = imageResponse2.jsonPath().getString("id");
            List<String> imageIds = List.of(id1, id2);

            feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds);
            commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);
        }

        @DisplayName("댓글 수정 요청시 성공하면, 응답코드 200을 응답한다")
        @Test
        void when_edit_comment_if_success_then_return_code_200() {
            // docs
            api_문서_타이틀("comment_edit_success", spec);

            // when
            var response = 댓글_수정한다(commentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_200을_반환한다(response);
        }

        @DisplayName("댓글 수정 요청시 요청 바디가 없으면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_request_body_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_edit_failed_by_request_body_not_exists", spec);

            // when
            var response = 댓글_없이_댓글_수정한다(commentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 수정 요청시 댓글이 비여있으면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_content_is_empty_then_return_code_400() {
            // docs
            api_문서_타이틀("editComment_failed_by_content_is_empty", spec);

            // when
            var response = 비여있는_댓글로_댓글_수정한다(commentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 수정 요청시 댓글이 공백이면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_content_is_space_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_edit_failed_by_content_is_blank", spec);

            // when
            var response = 공백인_댓글로_댓글_수정한다(commentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 수정 요청시 댓글이 200자를 초과하면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_content_is_larger_than_200_return_code_400() {
            // docs
            api_문서_타이틀("comment_edit_failed_by_content_is_larger_than_200", spec);

            // when
            var response = _201자인_댓글로_댓글_수정한다(commentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 수정 요청시 댓글이 이미 삭제되어 있으면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_comment_is_deleted_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_edit_failed_by_comment_is_deleted", spec);

            // given
            댓글을_삭제한다(commentId, feedId, 회원아티_액세스토큰);

            // when
            var response = 댓글_수정한다(commentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 수정 요청시 댓글이 존재하지 않으면 응답코드 400을 응답한다")
        @Test
        void when_edit_comment_if_comment_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_edit_failed_by_comment_not_exists", spec);

            // given
            String notExistsCommentId = "notExistsCommentId";

            // when
            var response = 댓글_수정한다(notExistsCommentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

    }

    @Nested
    @DisplayName("댓글 삭제 인수테스트")
    class DeleteFeedComment {

        String feedId;
        String commentId;

        @BeforeEach
        void setUp() {
            var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
            String id1 = imageResponse1.jsonPath().getString("id");
            var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
            String id2 = imageResponse2.jsonPath().getString("id");
            List<String> imageIds = List.of(id1, id2);

            feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds);
            commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);
        }

        @DisplayName("댓글 삭제 요청시 성공하면, 응답코드 200을 응답한다")
        @Test
        void when_delete_comment_if_success_then_return_code_200() {
            // docs
            api_문서_타이틀("comment_delete_success", spec);

            // when
            var response = 댓글을_삭제한다(commentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_200을_반환한다(response);
        }

        @DisplayName("댓글 삭제 요청시 댓글이 이미 삭제되어 있으면 응답코드 400을 응답한다")
        @Test
        void when_delete_comment_if_comment_is_deleted_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_delete_failed_by_comment_not_exists", spec);

            // given
            댓글을_삭제한다(commentId, feedId, 회원아티_액세스토큰);

            // when
            var response = 댓글을_삭제한다(commentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

        @DisplayName("댓글 삭제 요청시 댓글이 존재하지 않으면 응답코드 400을 응답한다")
        @Test
        void when_delete_comment_if_comment_not_exists_then_return_code_400() {
            // docs
            api_문서_타이틀("comment_delete_failed_by_comment_is_deleted", spec);
            String notExistsCommentId = "notExistsCommentId";

            // when
            var response = 댓글을_삭제한다(notExistsCommentId, feedId, 회원아티_액세스토큰, spec);

            // then
            응답코드_400_검증한다(response);
        }

    }

    @Nested
    @DisplayName("댓글 조회 인수테스트")
    class FetchComments {

        private String feedId;

        @BeforeEach
        void setUp() {
            var imageResponse1 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
            String id1 = imageResponse1.jsonPath().getString("id");
            var imageResponse2 = 피드_이미지를_업로드한다(회원아티_액세스토큰, spec);
            String id2 = imageResponse2.jsonPath().getString("id");
            List<String> imageIds = List.of(id1, id2);

            feedId = 피드를_등록하고_아이디를_받는다(회원아티_액세스토큰, imageIds);
            for (int i = 0; i < 20; i++) {
                피드에_댓글을_등록한다(feedId, 회원아티_액세스토큰);
            }
        }

        @DisplayName("댓글 조회 요청시 패이지와 사이즈 정보를 넣으면 해당 페이지와 사이즈의 댓글을 조회한다")
        @Test
        void when_fetch_comments_if_page_and_size_exists_then_return_page_and_size() {
            // docs
            api_문서_타이틀("comments_fetch_success", spec);

            // when
            var response = 페이지_적용_피드별_댓글을_조회한다(feedId, spec);

            // then
            페이지_적용_조회_검증(response);
        }

        @DisplayName("댓글에 좋아요가 누른 경우 댓글 조회 요청시 좋아요 여부를 반환한다")
        @Test
        void when_fetch_comments_if_liked_then_return_liked() {
            // docs
            api_문서_타이틀("comments_fetch_with_liked_success", spec);

            // given
            String commentId = 피드에_댓글을_등록하고_아이디를_받는다(feedId, 회원아티_액세스토큰);
            댓글에_좋아요를_누른다(commentId, feedId, 회원아티_액세스토큰, spec);

            // when
            var response = 피드별_댓글을_조회한다(회원아티_액세스토큰, feedId, spec, "0", "20");

            // then
            응답코드_200을_반환한다(response);
        }

        @DisplayName("accessToken와 함께 댓글 조회 요청시 패이지와 사이즈 정보를 넣으면 해당 페이지와 사이즈의 댓글을 조회한다.그리고 좋아요 여부를 반환한다")
        @Test
        void when_fetch_comments_if_page_and_size_exists_with_access_token_then_return_page_and_size() {
            // docs
            api_문서_타이틀("comments_fetch_with_with_access_token_success", spec);

            // when
            var response = 페이지_적용_피드별_댓글을_조회한다(회원아티_액세스토큰, feedId, spec);

            // then
            페이지_적용_조회_검증(response);
        }

        @DisplayName("댓글 조회 요청시 성공하면 응답코드 200을 응답한다")
        @Test
        void when_fetch_comments_if_success_then_return_code_200() {
            // docs
            api_문서_타이틀("comments_fetch_with_page_success", spec);

            // when
            var response = 피드별_댓글을_조회한다(feedId, spec);

            // then
            응답코드_200을_반환한다(response);
        }

    }

}
