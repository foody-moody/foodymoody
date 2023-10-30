package com.foodymoody.be.feed;

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

            List<Map<String, Object>> images = (List<Map<String, Object>>) feed.get("images");
            for (Map<String, Object> image : images) {
                assertThat(image).containsKeys("imageUrl", "menu");
                assertThat(((Map) image.get("menu"))).containsKeys("name", "rating");
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


    public static ExtractableResponse<Response> 피드를_등록한다() {
        return 피드를_등록한다(new RequestSpecBuilder().build());
    }

    public static String 피드를_등록하고_아이디를_받는다() {
        return 피드를_등록한다(new RequestSpecBuilder().build()).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 피드를_등록한다(RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "맛있게 매운 콩볼 범계점",
                "review", "맛있게 먹었습니다.",
                "storeMood", List.of("베지테리언", "무드1", "무드2"),
                "images", List.of(
                        Map.of(
                                "imageUrl", "https://www.googles.com/",
                                "menu", Map.of(
                                        "name", "마라탕",
                                        "rating", 4
                                )
                        ),
                        Map.of(
                                "imageUrl", "https://www.google.com/",
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
                () -> assertThat(response.jsonPath().getString("location")).isEqualTo("맛있게 매운 콩볼 범계점"),
                () -> assertThat(response.jsonPath().getString("review")).isEqualTo("맛있게 먹었습니다."),
                () -> assertThat(response.jsonPath().getList("storeMood", String.class)).containsExactly("베지테리언", "무드1",
                        "무드2"),
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
                    assertThat(images.get(0)).containsEntry("imageUrl", "https://www.googles.com/");
                    Map<String, Object> firstMenu = (Map<String, Object>) images.get(0).get("menu");
                    assertThat(firstMenu).containsEntry("name", "마라탕");
                    assertThat(firstMenu).containsEntry("rating", 4);

                    assertThat(images.get(1)).containsEntry("imageUrl", "https://www.google.com/");
                    Map<String, Object> secondMenu = (Map<String, Object>) images.get(1).get("menu");
                    assertThat(secondMenu).containsEntry("name", "감자탕");
                    assertThat(secondMenu).containsEntry("rating", 3);
                }
        );
    }

    public static ExtractableResponse<Response> 피드를_수정한다(String id, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "맛있게 매운 콩볼 범계점2",
                "review", "맛있게 먹었습니다.2",
                "storeMood", List.of("베지테리언2", "무드3", "무드4"),
                "images", List.of(
                        Map.of(
                                "imageUrl", "https://www.googles2.com/",
                                "menu", Map.of(
                                        "name", "마라탕2",
                                        "rating", 5
                                )
                        ),
                        Map.of(
                                "imageUrl", "https://www.google2.com/",
                                "menu", Map.of(
                                        "name", "감자탕2",
                                        "rating", 6
                                )
                        )
                )
        );

        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(body)
                .when()
                .put("/api/feeds/" + id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드를_삭제한다(String id, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .when()
                .delete("/api/feeds/" + id)
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_204라면_정상적으로_수정_삭제된_피드(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(204);
    }

}
