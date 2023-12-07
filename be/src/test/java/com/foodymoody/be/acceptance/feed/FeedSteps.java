package com.foodymoody.be.acceptance.feed;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
        // 1. Validate the status code
        assertThat(response.statusCode()).isEqualTo(200);

        // 2. Validate the structure of the 'content' array
        List<Map<String, Object>> content = response.jsonPath().getList("content");
        for (Map<String, Object> feed : content) {
            assertThat(feed).containsKeys("id", "member", "location", "review", "storeMood", "images", "likeCount",
                    "isLiked",
                    "commentCount");

            String createdAt = (String) feed.get("createdAt");
            String updatedAt = (String) feed.get("updatedAt");

            assertThat(createdAt).isNotNull();
            assertThat(updatedAt).isNotNull();

            try {
                LocalDateTime.parse(createdAt);
                LocalDateTime.parse(updatedAt);
            } catch (DateTimeParseException e) {
                fail("Invalid date-time format for createdAt or updatedAt");
            }

            // Modify storeMood validation here
            List<Map<String, Object>> storeMoods = (List<Map<String, Object>>) feed.get("storeMood");
            for (Map<String, Object> storeMood : storeMoods) {
                assertThat(storeMood).containsKeys("id", "name");

                assertThat(storeMood.get("id")).isInstanceOf(String.class); // Make sure 'id' is a Number
                assertThat(storeMood.get("name")).isInstanceOf(String.class); // Make sure 'name' is a String

                assertThat(storeMood.get("id")).isNotNull();
                assertThat(storeMood.get("name")).isNotNull();
            }

            List<Map<String, Object>> images = (List<Map<String, Object>>) feed.get("images");
            for (Map<String, Object> image : images) {
                assertThat(image).containsKeys("imageUrl", "menu");
//                assertThat(((Map) image.get("menu"))).containsKeys("name", "rating");
            }
        }

        // 3. Validate pagination metadata
        Map<String, Object> pageable = response.jsonPath().getMap("pageable");
        assertThat(pageable).containsKeys("sort", "pageNumber", "pageSize", "offset", "paged", "unpaged");

        Map<String, Object> sort = (Map<String, Object>) pageable.get("sort");
        assertThat(sort).containsKeys("unsorted", "sorted", "empty");

        // You can also validate specific pagination values if you have expected values, for example:
        // assertThat(pageable.get("pageNumber")).isEqualTo(0);
    }


    public static ExtractableResponse<Response> 피드를_등록한다(String accessToken) {
        return 피드를_등록한다(accessToken, new RequestSpecBuilder().build());
    }

    public static String 피드를_등록하고_아이디를_받는다(String accessToken) {
        return 피드를_등록한다(accessToken, new RequestSpecBuilder().build()).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 피드를_등록한다(String accessToken, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "역삼동",
                "review", "맛있어요!",
                "storeMood", List.of("1", "3", "4"),
                "images", List.of(
                        Map.of(
                                "imageId", "1",
                                "menu", Map.of(
                                        "name", "마라탕",
                                        "rating", 4
                                )
                        ),
                        Map.of(
                                "imageId", "2",
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

    public static ExtractableResponse<Response> 피드를_또_등록한다(String accessToken, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "중동",
                "review", "맛없어요!",
                "storeMood", List.of("1", "2", "4"),
                "images", List.of(
                        Map.of(
                                "imageId", "3",
                                "menu", Map.of(
                                        "name", "크림 파스타",
                                        "rating", 1
                                )
                        ),
                        Map.of(
                                "imageId", "4",
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
                assertThat(id)::isNotNull,
                () -> assertThat(response.jsonPath().getString("location")).isEqualTo("역삼동"),
                () -> assertThat(response.jsonPath().getString("review")).isEqualTo("맛있어요!"),
                () -> {
                    List<Map<String, String>> storeMoods = response.jsonPath().getList("storeMood");
                    assertThat(storeMoods).hasSize(3);

                    assertThat(storeMoods.get(0)).containsEntry("name", "가족과 함께");
                    assertThat(storeMoods.get(1)).containsEntry("name", "감성");
                    assertThat(storeMoods.get(2)).containsEntry("name", "데이트");
                },
                () -> {
                    String createdAt = response.jsonPath().getString("createdAt");
                    String updatedAt = response.jsonPath().getString("updatedAt");

                    assertThat(createdAt).isNotNull();
                    assertThat(updatedAt).isNotNull();

                    // Assuming you're using a standard ISO format (like "2023-10-17T16:54:03"), you can do:
                    try {
                        LocalDateTime.parse(createdAt);
                        LocalDateTime.parse(updatedAt);
                    } catch (DateTimeParseException e) {
                        fail("Invalid date-time format for createdAt or updatedAt");
                    }
                },
                () -> {
                    List<Map<String, Object>> images = response.jsonPath().getList("images");
                    assertThat(images.get(0)).containsEntry("imageUrl",
                            "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1");
                    Map<String, Object> firstMenu = (Map<String, Object>) images.get(0).get("menu");
                    assertThat(firstMenu).containsEntry("name", "마라탕");
                    assertThat(firstMenu).containsEntry("rating", 4);

                    assertThat(images.get(1)).containsEntry("imageUrl",
                            "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2");
                    Map<String, Object> secondMenu = (Map<String, Object>) images.get(1).get("menu");
                    assertThat(secondMenu).containsEntry("name", "감자탕");
                    assertThat(secondMenu).containsEntry("rating", 3);
                }
        );
    }

    public static ExtractableResponse<Response> 피드를_수정한다(String accessToken, String id, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "맛있게 매운 콩볼 범계점2",
                "review", "맛있게 먹었습니다.2",
                "storeMood", List.of("2", "5", "5"),
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
