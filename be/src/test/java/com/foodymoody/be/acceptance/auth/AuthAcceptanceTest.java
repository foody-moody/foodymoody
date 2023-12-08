package com.foodymoody.be.acceptance.auth;

import static com.foodymoody.be.acceptance.auth.AuthSteps.*;
import static com.foodymoody.be.acceptance.auth.AuthSteps.비회원보노가_로그인한다;
import static com.foodymoody.be.acceptance.auth.AuthSteps.상태코드_401과_오류코드_a005를_반환하는지_검증한다;
import static com.foodymoody.be.acceptance.auth.AuthSteps.상태코드_404와_오류코드_m001을_반환하는지_검증한다;
import static com.foodymoody.be.acceptance.auth.AuthSteps.토큰과_상태코드_200을_응답하는지_검증한다;
import static com.foodymoody.be.acceptance.auth.AuthSteps.푸반이_로그인한다;
import static com.foodymoody.be.acceptance.auth.AuthSteps.회원푸반이_틀린_비밀번호로_로그인한다;
import com.foodymoody.be.acceptance.AcceptanceTest;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("인증 관련 기능 인수테스트")
class AuthAcceptanceTest extends AcceptanceTest {

    @Nested
    @DisplayName("로그인 인수테스트")
    class Login {

        @DisplayName("로그인 요청시 성공하면 토큰을 반환한다.")
        @Test
        void when_login_then_return_200AndToken() {
            // docs
            api_문서_타이틀("login_success", spec);

            // when
            var response = 푸반이_로그인한다(spec);

            // then
            토큰과_상태코드_200을_응답하는지_검증한다(response);
        }

        @DisplayName("로그인 요청시 이메일에 해당하는 회원이 없으면 404와 오류코드 m001을 반환한다")
        @Test
        void when_loginFailedByUnregisteredEmail_then_return_400AndCodem001() {
            // docs
            api_문서_타이틀("login_failedByUnregisteredEmail", spec);

            // when
            var response = 비회원보노가_로그인한다(spec);

            // then
            상태코드_404와_오류코드_m001을_반환하는지_검증한다(response);
        }

        @DisplayName("로그인 요청시 비밀번호가 일치하지 않으면 401과 오류코드 a005를 반환한다")
        @Test
        void when_loginFailedByWrongPassword_then_return_400AndCodem001() {
            // docs
            api_문서_타이틀("login_failedByWrongPassword", spec);

            // when
            var response = 회원푸반이_틀린_비밀번호로_로그인한다(spec);

            // then
            상태코드_401과_오류코드_a005를_반환하는지_검증한다(response);
        }

    }

    @Nested
    @DisplayName("토큰 발급 테스트")
    class IssueToken {

        @Test
        void when_issueToken_then_success() throws InterruptedException {
            // docs
            api_문서_타이틀("issueToken_success", spec);

            // given
            var 푸반_로그인응답 = 푸반이_로그인한다(new RequestSpecBuilder().build());
            String 푸반_리프레시토큰 = 푸반_로그인응답.jsonPath().getString("refreshToken");

            // FIXME 토큰 생성 로직 수정 후 제거하겠습니다
            Thread.sleep(1000);

            // when
            var 푸반_토큰_재발급응답 = 토큰을_재발급한다(푸반_리프레시토큰, spec);

            // then
            상태코드가_200이고_새로운_토큰이_발급됐음을_검증한다(푸반_토큰_재발급응답, 푸반_로그인응답);

        }

        @Test
        void when_issueTokenWithInvalidRefreshToken_then_fail() {
            // docs
            api_문서_타이틀("issueTokenWithInvalidRefreshToken_fail", spec);

            // when
            var 푸반_토큰_재발급응답 = 토큰을_재발급한다("InvalidRefreshToken", spec);

            // then
            상태코드가_400이고_오류코드가_a002임을_검증한다(푸반_토큰_재발급응답);

        }

    }


//    @DisplayName("로그아웃 요청 성공하면 204코드를 반환한다.")
//    @Test
//    void when_logout_then_return_204() {
//        // docs
//        api_문서_타이틀("logout_success", spec);
//
//        // when
//        String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
//        var response = 로그아웃_한다(accessToken, spec);
//
//        // then
//        응답코드_204를_응답한다(response);
//    }
}
