package com.foodymoody.be.acceptance.image;

import static com.foodymoody.be.acceptance.image.ImageSteps.상태코드가_200이고_응답에_id와_url이_존재함을_검증한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.상태코드가_200임을_검증한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.상태코드가_400이고_오류코드가_i005임을_검증한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.상태코드가_400이고_오류코드가_i007임을_검증한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.상태코드가_401이고_오류코드가_a001임을_검증한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.상태코드가_404이고_오류코드가_i001임을_검증한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.이미지를_삭제한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.지원하지_않는_형식의_회원_이미지를_업로드한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.크기가_2_8MB를_넘는_회원_이미지를_업로드한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.회원_이미지를_업로드한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.IOException;
import java.time.LocalDateTime;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("이미지 관련 기능 인수테스트")
class ImageAcceptanceTest extends AcceptanceTest {

    @DisplayName("피드 이미지 업로드 테스트")
    @Nested
    class FeedImageUpload {

        @DisplayName("피드 이미지 업로드에 성공하면, 상태코드 200과 id를 반환한다")
        @Test
        void when_uploadFeedImage_then_response200AndIdAndUrl() {
            // docs
            api_문서_타이틀("uploadFeedImage_success", spec);

            // given

            // when
            var response = 피드_이미지를_업로드한다(회원푸반_액세스토큰, spec);

            // then
            상태코드가_200이고_응답에_id와_url이_존재함을_검증한다(response);
        }

    }

    @DisplayName("회원 이미지 업로드 테스트")
    @Nested
    class MemberProfileImageUpload {

        @DisplayName("회원 이미지 업로드에 성공하면, 상태코드 200과 id를 반환한다")
        @Test
        void when_uploadMemberProfileImage_then_response200AndIdAndUrl() {
            // docs
            api_문서_타이틀("uploadMemberProfileImage_success", spec);

            // when
            var response = 회원_이미지를_업로드한다(회원푸반_액세스토큰, spec);

            // then
            상태코드가_200이고_응답에_id와_url이_존재함을_검증한다(response);
        }

        @DisplayName("피드 이미지 크기가 2.8MB 넘으면, 상태코드 400과 오류코드 i007을 반환한다")
        @Test
        void when_uploadMemberImageOverSizeLimit_then_response400() throws IOException {
            // docs
            api_문서_타이틀("uploadFeedImageOverSizeLimit_Fail", spec);
            final byte[] file = IOUtils.toByteArray(getClass().getResourceAsStream("/images/2.8MB.png"));

            // when
            long startTime = System.currentTimeMillis();
            System.out.println("시작 시간 : " + LocalDateTime.now());
            var response = 크기가_2_8MB를_넘는_회원_이미지를_업로드한다(file, 회원푸반_액세스토큰, spec);
            long endTime = System.currentTimeMillis();
            System.out.println("실행 시간 : " + (endTime - startTime) / 1000.0);

            // then
            상태코드가_400이고_오류코드가_i007임을_검증한다(response);
        }

        @DisplayName("지원하지 않는 형식의 피드 이미지를 업로드하면, 상태코드 400과 오류코드 i005를 반환한다")
        @Test
        void when_uploadMemberImageWithUnsupportedFormat_then_response400() {
            // docs
            api_문서_타이틀("uploadFeedImageWithUnsupportedFormat_Fail", spec);

            // when
            var response = 지원하지_않는_형식의_회원_이미지를_업로드한다(회원푸반_액세스토큰, spec);

            // then
            상태코드가_400이고_오류코드가_i005임을_검증한다(response);
        }

    }

    @DisplayName("이미지 삭제 테스트")
    @Nested
    class Delete {

        @DisplayName("업로더가 이미지를 삭제하면, 상태코드 200을 반환한다")
        @Test
        void when_deleteFeedImageByUploader_then_response200() {
            // docs
            api_문서_타이틀("deleteFeedImage_success", spec);
            ExtractableResponse<Response> 이미지_업로드_응답 = 회원_이미지를_업로드한다(회원푸반_액세스토큰);
            String 이미지_아이디 = 이미지_업로드_응답.jsonPath().getString("id");

            // when
            var response = 이미지를_삭제한다(회원푸반_액세스토큰, 이미지_아이디, spec);

            // then
            상태코드가_200임을_검증한다(response);
        }

        @DisplayName("업로더가 아닌 회원이 이미지를 삭제하면, 상태코드 401과 오류코드 a001을 반환한다")
        @Test
        void when_deleteFeedImageNotByUploader_then_response200() {
            // docs
            api_문서_타이틀("deleteFeedImage_failedByUnAuthorized", spec);
            ExtractableResponse<Response> 이미지_업로드_응답 = 회원_이미지를_업로드한다(회원푸반_액세스토큰);
            String 이미지_아이디 = 이미지_업로드_응답.jsonPath().getString("id");

            // when
            var response = 이미지를_삭제한다(회원아티_액세스토큰, 이미지_아이디, spec);

            // then
            상태코드가_401이고_오류코드가_a001임을_검증한다(response);
        }

        @DisplayName("존재하지 않는 이미지 id이면, 상태코드 404과 오류코드 i001을 반환한다")
        @Test
        void when_deleteNotExistFeedImage_then_response404() {
            // docs
            api_문서_타이틀("deleteFeedImage_failedByNotExistId", spec);

            // when
            var response = 이미지를_삭제한다(회원푸반_액세스토큰, "invalidId", spec);

            // then
            상태코드가_404이고_오류코드가_i001임을_검증한다(response);
        }

    }

}
