package com.foodymoody.be.docs.member;

import static com.foodymoody.be.docs.member.MemberFixture.회원_보노;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MemberSteps {

    public static void 응답코드가_200이고_보노의_회원프로필이_조회되면_정상적으로_등록된_회원(ExtractableResponse<Response> response) {
        var 보노_회원프로필_조회_응답 = 회원프로필을_조회한다(회원_보노.getId());

        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(보노_회원프로필_조회_응답.statusCode()).isEqualTo(200),
                () -> assertThat(보노_회원프로필_조회_응답.jsonPath().getString("email")).isEqualTo(회원_보노.getEmail())
        );
    }

    public static ExtractableResponse<Response> 보노가_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = new HashMap<>();
        memberRegisterRequest.put("nickname", 회원_보노.getNickname());
        memberRegisterRequest.put("email", 회원_보노.getEmail());
        memberRegisterRequest.put("password", 회원_보노.getPassword());
        memberRegisterRequest.put("reconfirmPassword", 회원_보노.getPassword());
        memberRegisterRequest.put("mood", 회원_보노.getMood());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 보노가_회원가입한다() {
        Map<String, Object> memberRegisterRequest = new HashMap<>();
        memberRegisterRequest.put("nickname", 회원_보노.getNickname());
        memberRegisterRequest.put("email", 회원_보노.getEmail());
        memberRegisterRequest.put("password", 회원_보노.getPassword());
        memberRegisterRequest.put("reconfirmPassword", 회원_보노.getPassword());
        memberRegisterRequest.put("mood", 회원_보노.getMood());

        return 회원가입한다(memberRegisterRequest);
    }

    public static ExtractableResponse<Response> 보노의_회원프로필을_조회한다(RequestSpecification spec) {
        return 회원프로필을_조회한다(회원_보노.getId(), spec);
    }

    public static void 응답코드가_200이고_보노의_회원프로필이_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getLong("memberId")).isEqualTo(회원_보노.getId()),
                () -> assertThat(response.jsonPath().getString("myImageUrl")).isEqualTo(회원_보노.getMyImageUrl()),
                () -> assertThat(response.jsonPath().getString("nickname")).isEqualTo(회원_보노.getNickname()),
                () -> assertThat(response.jsonPath().getString("email")).isEqualTo(회원_보노.getEmail()),
                () -> assertThat(response.jsonPath().getString("mood")).isEqualTo(회원_보노.getMood())
        );
    }

    public static ExtractableResponse<Response> 보노가_회원탈퇴한다(RequestSpecification spec) {
        return 회원탈퇴한다(회원_보노.getId(), spec);
    }

    public static void 응답코드가_204이고_보노의_회원프로필이_조회되지_않는지_검증한다(ExtractableResponse<Response> response) {
        var bonoLoadMemberProfileResponse = 회원프로필을_조회한다(회원_보노.getId());

        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.NO_CONTENT),
                () -> 응답코드를_검증한다(bonoLoadMemberProfileResponse, HttpStatus.NOT_FOUND)
        );
    }

    public static ExtractableResponse<Response> 보노가_닉네임을_보노보노로_수정한다(RequestSpecification spec) {
        Map<String, Object> updateMemberProfileRequest = new HashMap<>();
        updateMemberProfileRequest.put("nickname", "보노보노");
        return 회원프로필을_수정한다(회원_보노.getId(), updateMemberProfileRequest, spec);
    }

    public static void 응답코드가_204이고_보노의_닉네임이_보노보노로_수정되었는지_검증한다(ExtractableResponse<Response> response) {
        var bonoLoadMemberProfileResponse = 회원프로필을_조회한다(회원_보노.getId());
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(bonoLoadMemberProfileResponse.jsonPath().getString("nickname")).isEqualTo("보노보노")
        );

    }

    public static void 응답코드가_204이고_수정_전의_비밀번호로_로그인에_실패하는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.NO_CONTENT)
                // TODO 수정 전 비밀번호로 로그인하면 로그인에 실패한다
        );
    }

    public static ExtractableResponse<Response> 보노의_회원_비밀번호를_수정한다(RequestSpecification spec) {
        Map<String, Object> updateMemberPasswordRequest = new HashMap<>();
        updateMemberPasswordRequest.put("password", "newpassword123!");
        updateMemberPasswordRequest.put("reconfirmPassword", "newpassword123!");

        return 회원비밀번호를_수정한다(회원_보노.getId(), updateMemberPasswordRequest, spec);
    }

    private static AbstractIntegerAssert<?> 응답코드를_검증한다(ExtractableResponse<Response> response,
            HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }

    private static ExtractableResponse<Response> 회원가입한다(Map<String, Object> memberRegisterRequest,
            RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .body(memberRegisterRequest)
                .when()
                .post("/api/members")
                .then()
                .log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 회원가입한다(Map<String, Object> memberRegisterRequest) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .body(memberRegisterRequest)
                .when()
                .post("/api/members")
                .then()
                .log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 회원프로필을_조회한다(Long memberId, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .when()
                .get("/api/members/{memberId}", memberId)
                .then()
                .log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 회원프로필을_조회한다(Long memberId) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .when()
                .get("/api/members/{memberId}", memberId)
                .then()
                .log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 회원탈퇴한다(long memberId, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .when()
                .delete("/api/members/{memberId}", memberId)
                .then()
                .log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 회원프로필을_수정한다(
            long memberId,
            Map<String, Object> updateMemberProfileRequest,
            RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .body(updateMemberProfileRequest)
                .log().all()
                .when()
                .put("/api/members/{memberId}", memberId)
                .then()
                .log().all()
                .extract();
    }

    private static ExtractableResponse<Response> 회원비밀번호를_수정한다(
            long memberId,
            Map<String, Object> updateMemberPasswordRequest,
            RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .body(updateMemberPasswordRequest)
                .log().all()
                .when()
                .put("/api/members/{memberId}/password", memberId)
                .then()
                .log().all()
                .extract();
    }
}