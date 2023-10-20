package com.foodymoody.be.docs.auth;

import static com.foodymoody.be.docs.auth.AuthSteps.로그아웃_한다;
import static com.foodymoody.be.docs.auth.AuthSteps.로그인_한다;
import static com.foodymoody.be.docs.auth.AuthSteps.응답코드_204를_응답한다;
import static com.foodymoody.be.docs.auth.AuthSteps.토큰과_응답코드_200을_응답한다;

import com.foodymoody.be.docs.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthTest extends Document {

    @DisplayName("로그인 요청시 성공하면 토큰을 반환한다.")
    @Test
    void when_login_then_return_token() {
        // docs
        api_문서_타이틀("login_success", spec);

        // when
        var response = 로그인_한다("test@example.com", spec);

        // then
        토큰과_응답코드_200을_응답한다(response);
    }

    @DisplayName("로그아웃 요청 성공하면 204코드를 반환한다.")
    @Test
    void when_logout_then_return_204() {
        // docs
        api_문서_타이틀("logout_success", spec);

        // when
        String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        var response = 로그아웃_한다(accessToken, spec);

        // then
        응답코드_204를_응답한다(response);
    }
}
