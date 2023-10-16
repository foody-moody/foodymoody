package com.foodymoody.be.docs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

public class FeedDocument extends Document {

    @DisplayName("피드를 등록한다.1")
    @Test
    void registerFeed() {
        spec.filter(document("registerFeed", Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));

        // given
        Map<String, Object> body = new HashMap<>();
        body.put("review", "맛있게 먹었습니다.");
        body.put("imageUrls", List.of("https://www.google.com/", "https://www.naver.com/"));
        body.put("menus", List.of(Map.of("name", "마라탕", "numStar", 4), Map.of("name", "떡볶이", "numStar", 5)));

        // when
        var response = RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(body)
                .when()
                .post("/api/feeds")
                .then()
                .log().all()
                .extract();

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getLong("id")).isEqualTo(1)
                //TODO 피드 조회시 등록한 피드가 조회된다.
        );
    }

    @DisplayName("피드를 등록한다.2")
    @Test
    void registerFeed2() {
        spec.filter(document("registerFeed", Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));

        // given
        Map<String, Object> body = new HashMap<>();
        body.put("review", "맛있게 먹었습니다.");
        body.put("imageUrls", List.of("https://www.google.com/", "https://www.naver.com/"));
        body.put("menus", List.of(Map.of("name", "마라탕", "numStar", 4), Map.of("name", "떡볶이", "numStar", 5)));

        // when
        var response = RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(body)
                .when()
                .post("/api/feeds")
                .then()
                .log().all()
                .extract();

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getLong("id")).isEqualTo(1)
                //TODO 피드 조회시 등록한 피드가 조회된다.
        );
    }


    @DisplayName("피드를 등록한다.3")
    @Test
    void registerFeed3() {
        spec.filter(document("registerFeed", Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())));

        // given
        Map<String, Object> body = new HashMap<>();
        body.put("review", "맛있게 먹었습니다.");
        body.put("imageUrls", List.of("https://www.google.com/", "https://www.naver.com/"));
        body.put("menus", List.of(Map.of("name", "마라탕", "numStar", 4), Map.of("name", "떡볶이", "numStar", 5)));

        // when
        var response = RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(body)
                .when()
                .post("/api/feeds")
                .then()
                .log().all()
                .extract();

        // then
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getLong("id")).isEqualTo(1)
                //TODO 피드 조회시 등록한 피드가 조회된다.
        );
    }

}
