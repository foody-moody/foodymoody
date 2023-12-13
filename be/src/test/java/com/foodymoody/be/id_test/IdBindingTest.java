package com.foodymoody.be.id_test;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.auth.util.JwtUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class IdBindingTest extends AcceptanceTest {

    @Autowired
    private JwtUtil jwtUtil;

    @DisplayName("path variable, request parameter, request body의 필드들, @LoginId, response의 필드들을 BaseId의 하위 클래스로 바인딩한다")
    @Test
    void bindingTest() {

        // given
        String 푸반_아이디 = jwtUtil.parseAccessToken(회원푸반_액세스토큰).get("id");

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .auth().oauth2(회원푸반_액세스토큰)
                .params("paramId", "testParamId")
                .body(Map.of("memberId", "testRequestMemberId",
                        "feedId", "testRequestFeedId",
                        "commentId", "testRequestCommentId"))
                .when().log().all()
                .get("/binding-test/{pathId}", "testPathId")
                .then().log().all().extract();

        // then
        Assertions.assertAll(
                () -> assertThat(response.jsonPath().getString("memberId")).isEqualTo("testRequestMemberId"),
                () -> assertThat(response.jsonPath().getString("feedId")).isEqualTo("testRequestFeedId"),
                () -> assertThat(response.jsonPath().getString("commentId")).isEqualTo("testRequestCommentId"),
                () -> assertThat(response.jsonPath().getString("pathId")).isEqualTo("testPathId"),
                () -> assertThat(response.jsonPath().getString("annotationId")).isEqualTo(푸반_아이디),
                () -> assertThat(response.jsonPath().getString("paramId")).isEqualTo("testParamId")
        );
    }
}
