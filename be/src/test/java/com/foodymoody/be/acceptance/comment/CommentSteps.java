package com.foodymoody.be.acceptance.comment;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;

public class CommentSteps {

    public static ExtractableResponse<Response> 피드에_댓글을_등록한다(long feedId, RequestSpecification spec) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "댓글 내용");
        body.put("feedId", feedId);
        return RestAssured.given().spec(spec).log().all()
                .body(body).contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/comments")
                .then().log().all()
                .extract();
    }

    public static void 응답코드_200_검증한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(200);
    }
}
