package com.foodymoody.be.acceptance.store;

import static com.foodymoody.be.acceptance.feed.FeedSteps.특정_가게의_피드를_등록한다;
import static com.foodymoody.be.acceptance.image.ImageSteps.피드_이미지를_업로드한다;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.menu.util.MenuFixture;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class StoreSteps {

    public static ExtractableResponse<Response> 식당_상세정보를_조회한다(RequestSpecification spec, String id) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/stores/{id}", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 식당을_검색한다(RequestSpecification spec, String query) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .params("query", query)
                .get("/api/stores/search")
                .then()
                .log().all()
                .extract();
    }

    public static void 이미지를_업로드하고_특정_id의_가게에_대한_피드를_등록한다(String accessToken, String id) {
        ExtractableResponse<Response> 피드_이미지1_업로드_응답 =
                피드_이미지를_업로드한다(accessToken, new RequestSpecBuilder().build());
        String 피드_이미지1_아이디 = 피드_이미지1_업로드_응답.jsonPath().getString("id");
        ExtractableResponse<Response> 피드_이미지2_업로드_응답 =
                피드_이미지를_업로드한다(accessToken, new RequestSpecBuilder().build());
        String 피드_이미지2_아이디 = 피드_이미지2_업로드_응답.jsonPath().getString("id");
        특정_가게의_피드를_등록한다(
                accessToken,
                id,
                List.of(피드_이미지1_아이디, 피드_이미지2_아이디),
                List.of(
                        MenuFixture.getFeedRegisterRequestMenuWithRating(3),
                        MenuFixture.getFeedRegisterRequestMenuWithRating(2)
                        ));
    }

    public static AbstractIntegerAssert<?> 상태코드를_검증한다(ExtractableResponse<Response> response,
            HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }

    public static AbstractStringAssert<?> 오류코드를_검증한다(ExtractableResponse<Response> response, String code) {
        return assertThat(response.jsonPath().getString("code")).isEqualTo(code);
    }

}
