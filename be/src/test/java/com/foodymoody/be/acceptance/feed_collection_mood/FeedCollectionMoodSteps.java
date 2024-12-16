package com.foodymoody.be.acceptance.feed_collection_mood;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

public class FeedCollectionMoodSteps {

    public static ExtractableResponse<Response> 피드_컬렉션_무드를_등록한다(String accessToken, RequestSpecification spec) {
        var body = Map.of("name", "행복");
        return RestAssured
                .given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when().post("/api/feed_collections/moods")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션_무드를_등록한다(String accessToken) {
        return 피드_컬렉션_무드를_등록한다(accessToken, new RequestSpecBuilder().build());
    }

    public static String 피드_컬렉션_무드를_등록하고_아이디를_가져온다(String accessToken) {
        return 피드_컬렉션_무드를_등록한다(accessToken).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 전체_피드_컬렉션_무드를_조회한다(RequestSpecification spec, String accessToken) {
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/api/feed_collections/moods")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션_무드를_추가한다(
            String feedCollectionId,
            String moodId,
            String accessToken,
            RequestSpecification spec
    ) {
        var body = Map.of("moodId", moodId);
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .body(body).contentType("application/json")
                .when()
                .post("/api/feed_collections/" + feedCollectionId + "/moods")
                .then().log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드_컬렉션_무드를_추가한다(
            String feedCollectionId,
            String moodId,
            String accessToken
    ) {
        return 피드_컬렉션_무드를_추가한다(feedCollectionId, moodId, accessToken, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 피드_컬렉션_무드를_제거한다(
            String feedCollectionId,
            String moodId,
            String accessToken,
            RequestSpecification spec
    ) {
        return RestAssured.given()
                .spec(spec)
                .log().all()
                .auth().oauth2(accessToken)
                .when()
                .delete("/api/feed_collections/{feedCollectionId}/moods/{moodId}", feedCollectionId, moodId)
                .then().log().all()
                .extract();
    }

}
