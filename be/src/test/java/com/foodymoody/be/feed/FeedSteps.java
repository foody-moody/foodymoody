package com.foodymoody.be.feed;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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
            assertThat(feed).containsKeys("id", "member", "location", "review", "mood", "images", "likeCount", "commentCount");
            List<Map<String, Object>> images = (List<Map<String, Object>>) feed.get("images");
            for (Map<String, Object> image : images) {
                assertThat(image).containsKeys("imageUrl", "menu");
                assertThat(((Map) image.get("menu"))).containsKeys("name", "numStar");
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


    public static ExtractableResponse<Response> 피드를_등록한다(RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "맛있게 매운 콩볼 범계점",
                "review", "맛있게 먹었습니다.",
                "mood", "기쁨",
                "images", List.of(
                        Map.of(
                                "imageUrl", "https://www.googles.com/",
                                "menu", Map.of(
                                        "name", "마라탕",
                                        "numStar", 4
                                )
                        ),
                        Map.of(
                                "imageUrl", "https://www.google.com/",
                                "menu", Map.of(
                                        "name", "감자탕",
                                        "numStar", 3
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
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getLong("id")).isNotNull()
        );
    }

    public static ExtractableResponse<Response> 개별_피드를_조회한다(Long id, RequestSpecification spec) {
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
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getLong("id")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("location")).isEqualTo("맛있게 매운 콩볼 범계점"),
                () -> assertThat(response.jsonPath().getString("review")).isEqualTo("맛있게 먹었습니다."),
                () -> assertThat(response.jsonPath().getString("mood")).isEqualTo("기쁨"),
                // TODO: BaseEntity 구현 후 createdAt, updatedAt 추가
                () -> {
                    List<Map<String, Object>> images = response.jsonPath().getList("images");
                    assertThat(images.get(0)).containsEntry("imageUrl", "https://www.googles.com/");
                    Map<String, Object> firstMenu = (Map<String, Object>) images.get(0).get("menu");
                    assertThat(firstMenu).containsEntry("name", "마라탕");
                    assertThat(firstMenu).containsEntry("numStar", 4);

                    assertThat(images.get(1)).containsEntry("imageUrl", "https://www.google.com/");
                    Map<String, Object> secondMenu = (Map<String, Object>) images.get(1).get("menu");
                    assertThat(secondMenu).containsEntry("name", "감자탕");
                    assertThat(secondMenu).containsEntry("numStar", 3);
                }
        );
    }

    public static ExtractableResponse<Response> 피드를_수정한다(Long id, RequestSpecification spec) {
        Map<String, Object> body = Map.of(
                "location", "맛있게 매운 콩볼 범계점2",
                "review", "맛있게 먹었습니다.2",
                "mood", "기쁨2",
                "images", List.of(
                        Map.of(
                                "imageUrl", "https://www.googles2.com/",
                                "menu", Map.of(
                                        "name", "마라탕2",
                                        "numStar", 5
                                )
                        ),
                        Map.of(
                                "imageUrl", "https://www.google2.com/",
                                "menu", Map.of(
                                        "name", "감자탕2",
                                        "numStar", 6
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

    public static ExtractableResponse<Response> 피드를_삭제한다(Long id, RequestSpecification spec) {
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
