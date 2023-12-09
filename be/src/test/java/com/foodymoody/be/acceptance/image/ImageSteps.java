package com.foodymoody.be.acceptance.image;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ImageSteps {

    public static ExtractableResponse<Response> 피드_이미지를_업로드한다(String accessToken, RequestSpecification spec) {
        return RestAssured.given().spec(spec).auth().oauth2(accessToken)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE).multiPart("file", getFile("images/potato.jpg"))
                .log().all()
                .when().post("/api/images/feeds")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 크기가_2_8MB를_넘는_회원_이미지를_업로드한다(byte[] file, String accessToken,
            RequestSpecification spec) {
        return RestAssured.given().spec(spec).auth().oauth2(accessToken)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE).multiPart("file", "myFile.png", file)
                .log().all()
                .when().post("/api/images/members")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원_이미지를_업로드한다(String accessToken, RequestSpecification spec) {
        return RestAssured.given().spec(spec).auth().oauth2(accessToken)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE).multiPart(getFile("images/potato.jpg"))
                .log().all()
                .when().post("/api/images/members")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원_이미지를_업로드한다(String accessToken) {
        return RestAssured.given().auth().oauth2(accessToken)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE).multiPart(getFile("images/potato.jpg"))
                .log().all()
                .when().post("/api/images/members")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 이미지를_삭제한다(String accessToken, String id, RequestSpecification spec) {
        return RestAssured.given().spec(spec).auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .when().delete("/api/images/{id}", id)
                .then()
                .log().all()
                .extract();
    }

    public static void 상태코드가_200임을_검증한다(ExtractableResponse<Response> response) {
        상태코드를_검증한다(response, HttpStatus.OK);
    }

    public static void 상태코드가_400임을_검증한다(ExtractableResponse<Response> response) {
        상태코드를_검증한다(response, HttpStatus.BAD_REQUEST);
    }

    public static void 상태코드가_404이고_오류코드가_i001임을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.NOT_FOUND),
                () -> 오류코드를_검증한다(response, "i001")
        );
    }

    public static void 상태코드가_401이고_오류코드가_a001임을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.UNAUTHORIZED),
                () -> 오류코드를_검증한다(response, "a001")
        );
    }

    public static void 상태코드가_400이고_오류코드가_i005임을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "i005")
        );
    }

    public static void 상태코드가_400이고_오류코드가_i007임을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "i007")
        );
    }

    public static void 상태코드가_200이고_응답에_id와_url이_존재함을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getString("id")).isNotBlank(),
                () -> assertThat(response.jsonPath().getString("url")).isNotBlank()
        );
    }

    public static AbstractIntegerAssert<?> 상태코드를_검증한다(ExtractableResponse<Response> response,
            HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }

    static ExtractableResponse<Response> 지원하지_않는_형식의_회원_이미지를_업로드한다(String accessToken, RequestSpecification spec) {
        return RestAssured.given().spec(spec).auth().oauth2(accessToken)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE).multiPart(getFile("images/test.txt"))
                .log().all()
                .when().post("/api/images/members")
                .then()
                .log().all()
                .extract();
    }

    static File getFile(String path) {
        Resource resource = new ClassPathResource(path);
        try {
            return resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static AbstractStringAssert<?> 오류코드를_검증한다(ExtractableResponse<Response> response, String code) {
        return assertThat(response.jsonPath().getString("code")).isEqualTo(code);
    }
}
