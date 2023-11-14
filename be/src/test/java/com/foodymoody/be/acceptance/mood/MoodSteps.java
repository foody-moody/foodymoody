package com.foodymoody.be.acceptance.mood;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.mood.MoodFixture;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MoodSteps {

    public static ExtractableResponse<Response> 랜덤_무드_3개를_조회한다 (RequestSpecification spec) {
        return 무드를_랜덤_조회한다(3, spec);
    }

    public static ExtractableResponse<Response> page는1_size는3인_무드_슬라이스를_조회한다(RequestSpecification spec) {
        return 무드_슬라이스를_조회한다(1, 3, spec);
    }

    public static ExtractableResponse<Response> 베지테리언_무드를_id로_조회한다(RequestSpecification spec) {
        return 개별_무드를_id로_조회한다(MoodFixture.ID, spec);
    }

    public static ExtractableResponse<Response> 얼죽아_무드를_추가한다(RequestSpecification spec) {
        Map<String, Object> request = Map.of("mood", "얼죽아");
        return 무드를_추가한다(request, spec);
    }

    public static ExtractableResponse<Response> 베지테리언_무드를_추가한다(RequestSpecification spec) {
        return 무드를_추가한다(Map.of("mood", MoodFixture.NAME), spec);
    }

    public static ExtractableResponse<Response> null인_무드를_추가한다(RequestSpecification spec) {
        HashMap<String, Object> request = new HashMap<>();
        request.put("mood", null);
        return 무드를_추가한다(request, spec);
    }

    public static void 응답코드가_200이고_추가된_무드의_id에_해당하는_무드가_조회되는지_검증한다(ExtractableResponse<Response> response) {
        String 얼죽아_아이디 = response.jsonPath().getString("id");
        ExtractableResponse<Response> 얼죽아조회_응답 = 개별_무드를_id로_조회한다(얼죽아_아이디, new RequestSpecBuilder().build());
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> org.assertj.core.api.Assertions.assertThat(얼죽아조회_응답.jsonPath().getString("name"))
                        .isEqualTo("얼죽아")
        );
    }

    public static ExtractableResponse<Response> 무드를_조회한다(RequestSpecification spec) {
        return RestAssured.given().log().all()
                .spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/moods")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 무드_슬라이스를_조회한다(int page, int size, RequestSpecification spec) {
        return RestAssured.given().log().all()
                .spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .params("page", page, "size", size)
                .get("/api/moods")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> count_없이_랜덤_무드_3개를_조회한다(RequestSpecification spec) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .when()
                .get("/api/moods/random")
                .then()
                .log().all()
                .extract();
    }

    public static void 응답코드가_200이고_빈_리스트가_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getList("content")).isEmpty()
        );

    }

    public static void 응답코드가_400이고_예외가_발생하는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> assertThat(response.jsonPath().getString("code")).isEqualTo("g001")
        );
    }

    public static void 응답코드가_200이고_3개의_무드가_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getList("content")).hasSize(3)
        );
    }

    public static void 응답코드가_200이고_페이지가_0이고_사이즈가_10인_무드_슬라이스가_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getInt("pageable.pageNumber")).isZero(),
                () -> assertThat(response.jsonPath().getInt("pageable.pageSize")).isEqualTo(10)
        );
    }

    public static void 응답코드가_200이고_페이지가_1이고_사이즈가_3인_무드_슬라이스가_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> org.assertj.core.api.Assertions.assertThat(response.jsonPath().getInt("pageable.pageNumber")).isEqualTo(1),
                () -> org.assertj.core.api.Assertions.assertThat(response.jsonPath().getInt("pageable.pageSize")).isEqualTo(3)
        );

    }

    public static void 응답코드가_200이고_베지테리언이_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getString("name")).isEqualTo(MoodFixture.NAME)
        );
    }

    public static void 응답코드가_400임을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.BAD_REQUEST)
        );

    }

    private static ExtractableResponse<Response> 무드를_랜덤_조회한다(int count, RequestSpecification spec) {
        return RestAssured.given().log().all()
                .spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .params("count", count)
                .when()
                .get("/api/moods/random")
                .then()
                .log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 무드를_추가한다(Map<String, Object> request, RequestSpecification spec) {
        return RestAssured.given().log().all()
                .spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(request)
                .post("/api/moods")
                .then()
                .log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 개별_무드를_id로_조회한다(String id, RequestSpecification spec) {
        return RestAssured.given().log().all()
                .spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/moods/{id}", id)
                .then()
                .log().all()
                .extract();
    }

    private static AbstractIntegerAssert<?> 응답코드를_검증한다(ExtractableResponse<Response> response,
            HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }
}
