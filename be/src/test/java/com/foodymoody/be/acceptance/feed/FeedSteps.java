package com.foodymoody.be.acceptance.feed;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.MediaType;

public class FeedSteps {

    public static ExtractableResponse<Response> 전체_피드를_조회한다(RequestSpecification spec, int page, int size) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .param("page", page)
                .param("size", size)
                .log().all()
                .when()
                .get("/api/feeds")
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_200이고_전체_피드가_조회되면_정상적으로_조회_가능한_전체_페이지(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(200);

        Map<String, Object> pageable = response.jsonPath().getMap("pageable");
        assertThat(pageable).containsKeys("sort", "pageNumber", "pageSize", "offset", "paged", "unpaged");

        Map<String, Object> sort = (Map<String, Object>) pageable.get("sort");
        assertThat(sort).containsKeys("unsorted", "sorted", "empty");
    }


    public static ExtractableResponse<Response> 피드를_등록한다(String accessToken, List<String> imageIds) {
        return 피드를_등록한다(accessToken, new RequestSpecBuilder().build(), imageIds);
    }

    public static ExtractableResponse<Response> 피드를_또_등록한다(String accessToken, List<String> imageIds) {
        return 피드를_또_등록한다(accessToken, new RequestSpecBuilder().build(), imageIds);
    }

    public static String 피드를_등록하고_아이디를_받는다(String accessToken, List<String> imageIds) {
        return 피드를_등록한다(accessToken, new RequestSpecBuilder().build(), imageIds).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 피드를_등록한다(String accessToken, RequestSpecification spec, List<String> imageIds) {
        Map<String, Object> body = Map.of(
                "location", "역삼동",
                "review", "맛있어요!",
                "storeMoodIds", List.of("1", "3", "4"),
                "images", List.of(
                        Map.of(
                                "imageId", imageIds.get(0),
                                "menu", Map.of(
                                        "name", "마라탕",
                                        "rating", 4
                                )
                        ),
                        Map.of(
                                "imageId", imageIds.get(1),
                                "menu", Map.of(
                                        "name", "감자탕",
                                        "rating", 3
                                )
                        )
                )
        );

        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .body(body)
                .when()
                .post("/api/feeds")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드를_또_등록한다(String accessToken, RequestSpecification spec, List<String> imageIds) {
        Map<String, Object> body = Map.of(
                "location", "중동",
                "review", "맛없어요!",
                "storeMoodIds", List.of("1", "2", "4"),
                "images", List.of(
                        Map.of(
                                "imageId", imageIds.get(0),
                                "menu", Map.of(
                                        "name", "크림 파스타",
                                        "rating", 1
                                )
                        ),
                        Map.of(
                                "imageId", imageIds.get(1),
                                "menu", Map.of(
                                        "name", "토마토 파스타",
                                        "rating", 2
                                )
                        )
                )
        );

        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .body(body)
                .when()
                .post("/api/feeds")
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_200이고_id가_존재하면_정상적으로_등록된_피드(ExtractableResponse<Response> response) {
        String id = response.jsonPath().getString("id");
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                assertThat(id)::isNotNull
        );
    }

    public static ExtractableResponse<Response> 개별_피드를_조회한다(String id, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .when()
                .get("/api/feeds/" + id)
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_200이고_개별_피드가_조회되면_정상적으로_등록된_피드(ExtractableResponse<Response> response) {
        Object id = response.jsonPath().getString("id");
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                assertThat(id)::isNotNull
        );

    }

    public static ExtractableResponse<Response> 피드를_수정한다(String accessToken, String id, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "맛있게 매운 콩볼 범계점2",
                "review", "맛있게 먹었습니다.2",
                "storeMoodIds", List.of("2", "5", "6"),
                "images", List.of(
                        Map.of(
                                "imageId", "3",
                                "menu", Map.of(
                                        "name", "마라탕2",
                                        "rating", 5
                                )
                        ),
                        Map.of(
                                "imageId", "4",
                                "menu", Map.of(
                                        "name", "감자탕2",
                                        "rating", 5
                                )
                        )
                )
        );

        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .body(body)
                .when()
                .put("/api/feeds/" + id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드를_삭제한다(String accessToken, String id, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .auth()
                .oauth2(accessToken)
                .when()
                .delete("/api/feeds/" + id)
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_204라면_정상적으로_수정_삭제된_피드(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(204);
    }


    public static ExtractableResponse<Response> 전체_스토어_무드를_조회한다(RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .when()
                .get("/api/feeds/store-moods")
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_200이고_전체_스토어_무드가_조회되면_정상적으로_조회_가능한_전체_스토어_무드(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getList("")).hasSize(6)
        );

    }
}
