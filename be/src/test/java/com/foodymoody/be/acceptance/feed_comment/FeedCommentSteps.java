package com.foodymoody.be.acceptance.feed_comment;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class FeedCommentSteps {

    public static ExtractableResponse<Response> 피드에_댓글을_등록한다(
            String feedId, String accessToken,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "댓글 내용");
        return 피드에_댓글_등록_요청한다(spec, accessToken, body, feedId);
    }

    public static ExtractableResponse<Response> 피드에_댓글을_등록한다(String feedId, String accessToken) {
        return 피드에_댓글을_등록한다(feedId, accessToken, new RequestSpecBuilder().build());
    }

    public static String 피드에_댓글을_등록하고_아이디를_받는다(String feedId, String accessToken) {
        return 피드에_댓글을_등록한다(feedId, accessToken, new RequestSpecBuilder().build()).jsonPath().getString("id");
    }

    public static void 응답코드_201과_id를_반환한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(201),
                () -> assertThat(response.body().jsonPath().getString("id")).isNotNull()
        );
    }

    public static void 응답코드_200을_반환한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(200);
    }

    public static void 응답코드_201을_반환한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(201);
    }

    public static void 응답코드_400_검증한다(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    public static ExtractableResponse<Response> 댓글없이_피드에_댓글_등록한다(
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {

        return 피드에_댓글_등록_요청한다(spec, accessToken, new HashMap<>(), feedId);
    }

    public static ExtractableResponse<Response> 피드에_공백댓글_등록한다(
            String feedId, String accessToken,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "");
        return 피드에_댓글_등록_요청한다(spec, accessToken, body, feedId);
    }

    public static ExtractableResponse<Response> 피드에_여러_공백댓글_등록한다(
            String feedId, String accessToken,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "   ");
        return 피드에_댓글_등록_요청한다(spec, accessToken, body, feedId);
    }

    public static ExtractableResponse<Response> 피드에서_200자_넘는_댓글을_등록한다(
            String feedId, String accessToken,
            RequestSpecification spec
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("content", "200자".repeat(50) + "1");
        body.put("feedId", feedId);
        return 피드에_댓글_등록_요청한다(spec, accessToken, body, feedId);
    }

    public static ExtractableResponse<Response> 요청_내용_없이_댓글_등록한다(
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured
                .given().spec(spec).log().all().auth().oauth2(accessToken)
                .contentType("application/json")
                .when().post("/api/feed/{feedId}/comments", feedId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 댓글_수정한다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        Map<String, String> body = new HashMap<>();
        body.put("content", "수정된 댓글");
        return 댓글_수정_요청한다(commentId, feedId, body, accessToken, spec);
    }


    public static ExtractableResponse<Response> 댓글_없이_댓글_수정한다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        return 댓글_수정_요청한다(commentId, feedId, new HashMap<>(), accessToken, spec);
    }

    public static ExtractableResponse<Response> 비여있는_댓글로_댓글_수정한다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        Map<String, String> body = new HashMap<>();
        body.put("content", "");
        return 댓글_수정_요청한다(commentId, feedId, body, accessToken, spec);
    }

    public static ExtractableResponse<Response> 공백인_댓글로_댓글_수정한다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        Map<String, String> body = new HashMap<>();
        body.put("content", "   ");
        return 댓글_수정_요청한다(commentId, feedId, body, accessToken, spec);
    }

    public static ExtractableResponse<Response> _201자인_댓글로_댓글_수정한다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        Map<String, String> body = new HashMap<>();
        body.put("content", "a".repeat(201));
        return 댓글_수정_요청한다(commentId, feedId, body, accessToken, spec);
    }

    public static ExtractableResponse<Response> 댓글을_삭제한다(
            String commentId,
            String feedId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured.given().spec(spec).log().all()
                .auth().oauth2(accessToken)
                .when().delete("/api/feed/{feedId}/comments/{commentId}", feedId, commentId)
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 댓글을_삭제한다(String commentId, String feedId, String accessToken) {
        return 댓글을_삭제한다(commentId, feedId, accessToken, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 피드별_댓글을_조회한다(String feedId, RequestSpecification spec) {
        return 피드별_댓글을_조회한다(feedId, spec, "0", "10");
    }

    public static ExtractableResponse<Response> 페이지_적용_피드별_댓글을_조회한다(String feedId, RequestSpecification spec) {
        return 피드별_댓글을_조회한다(feedId, spec, "1", "5");
    }

    public static ExtractableResponse<Response> 페이지_적용_피드별_댓글을_조회한다(
            String accessToken, String feedId,
            RequestSpecification spec
    ) {
        return 피드별_댓글을_조회한다(accessToken, feedId, spec, "1", "5");
    }

    public static ExtractableResponse<Response> 피드별_댓글을_조회한다(
            String accessToken,
            String feedId,
            RequestSpecification spec,
            String page,
            String size
    ) {
        Map<String, String> params = new HashMap<>();
        params.put("feedId", feedId);
        params.put("page", page);
        params.put("size", size);
        return RestAssured.given().spec(spec).log().all().auth().oauth2(accessToken)
                .params(params)
                .when()
                .get("/api/feed/{feedId}/comments", feedId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드별_댓글을_조회한다(
            String feedId,
            RequestSpecification spec,
            String page,
            String size
    ) {
        Map<String, String> params = new HashMap<>();
        params.put("page", page);
        params.put("size", size);
        return RestAssured.given().log().all()
                .spec(spec)
                .params(params)
                .when()
                .get("/api/feed/{feedId}/comments", feedId)
                .then()
                .log().all()
                .extract();
    }

    public static void 페이지_적용_조회_검증(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.body().jsonPath().getLong("number")).isEqualTo(1),
                () -> assertThat(response.body().jsonPath().getLong("size")).isEqualTo(5)
        );
    }

    private static ExtractableResponse<Response> 피드에_댓글_등록_요청한다(
            RequestSpecification spec, String accessToken,
            Map<String, Object> body, String feedId
    ) {
        return RestAssured.given().spec(spec).log().all().auth().oauth2(accessToken)
                .body(body).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/feed/{feedId}/comments", feedId)
                .then().log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 댓글_수정_요청한다(
            String commentId,
            String feedId,
            Map<String, String> body,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured
                .given().log().all().spec(spec).contentType("application/json").body(body).auth().oauth2(accessToken)
                .when().put("/api/feed/{feedId}/comments/{commentId}", feedId, commentId)
                .then().log().all()
                .extract();
    }
}
