package com.foodymoody.be.docs.auth;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.docs.Document;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AuthTest extends Document {


    @DisplayName("로그인 요청시 성공하면 토큰을 반환한다.")
    @Test
    void when_login_then_return_token() {
        // docs
        api_문서_타이틀("login_success", spec);

        // given
        Map<String, String> body = new HashMap<>();
        body.put("email", "test@example.com");
        body.put("password", "password");
        var response = RestAssured
                .given().spec(spec).log().all()
                .body(body)
                .when().post("/auth/login")
                .then().log().all()
                .extract();

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getString("accessToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("refreshToken")).isNotNull()
        );
    }
}
