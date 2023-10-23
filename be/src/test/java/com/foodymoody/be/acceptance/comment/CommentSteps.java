package com.foodymoody.be.acceptance.comment;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CommentSteps {

    public static ExtractableResponse<Response> 피드에_댓글을_등록한다(long feedId, RequestSpecification spec) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "댓글 내용");
        body.put("feedId", feedId);
        return RestAssured.given().spec(spec).log().all()
                .body(body).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/comments")
                .then().log().all()
                .extract();
    }

    public static void 응답코드_200_검증한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(200);
    }

    public static void 응답코드_400_검증한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static ExtractableResponse<Response> 댓글없이_피드에_댓글_등록한다(long feedId, RequestSpecification spec) {

        return RestAssured.given().log().all()
                .given().spec(spec).log().all()
                .body(Map.of("feedId", feedId))
                .contentType("application/json")
                .when().post("/api/comments")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드에_공백댓글_등록한다(long feedId, RequestSpecification spec) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "");
        body.put("feedId", feedId);
        return RestAssured
                .given().spec(spec).log().all()
                .body(body).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/comments")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드에_여러_공백댓글_등록한다(long feedId, RequestSpecification spec) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "   ");
        body.put("feedId", feedId);
        return RestAssured
                .given().spec(spec).log().all()
                .body(body).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/comments")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_아이디_없이_댓글을_등록한다(RequestSpecification spec) {
        Map<String, String> body = new HashMap<>();
        body.put("content", "댓글 내용");
        return RestAssured
                .given().spec(spec).log().all()
                .body(body)
                .contentType("application/json")
                .when().post("/api/comments")
                .then().log().all()
                .extract();
    }


    public static ExtractableResponse<Response> 피드에서_200자_넘는_댓글을_등록한다(long feedId, RequestSpecification spec) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "200자".repeat(50) + "1");
        body.put("feedId", feedId);
        return RestAssured.given().spec(spec).log().all()
                .body(body).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/comments")
                .then().log().all()
                .extract();
    }


    public static ExtractableResponse<Response> 요청_내용_없이_댓글_등록한다(RequestSpecification spec) {
        return RestAssured
                .given().spec(spec).log().all()
                .contentType("application/json")
                .when().post("/api/comments")
                .then().log().all()
                .extract();
    }
}
