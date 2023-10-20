package com.foodymoody.be.docs.member;

import static com.foodymoody.be.docs.member.MemberFixture.회원_보노;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MemberSteps {

    private static final RequestSpecification FAKE_SPEC = RestAssured.given();

    public static ExtractableResponse<Response> 회원보노가_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 회원_보노.getNickname(),
                "email", 회원_보노.getEmail(),
                "password", 회원_보노.getPassword(),
                "reconfirmPassword", 회원_보노.getPassword(),
                "mood", 회원_보노.getMood());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 회원보노의_회원프로필을_조회한다(RequestSpecification spec) {
        return 회원프로필을_조회한다(회원_보노.getId(), spec);
    }

    public static ExtractableResponse<Response> 회원보노가_회원탈퇴한다(RequestSpecification spec) {
        return 회원탈퇴한다(회원_보노.getId(), spec);
    }

    public static ExtractableResponse<Response> 회원보노가_닉네임을_보노보노로_수정한다(RequestSpecification spec) {
        Map<String, Object> updateMemberProfileRequest = Map.of("nickname", "보노보노");
        return 회원프로필을_수정한다(회원_보노.getId(), updateMemberProfileRequest, spec);
    }

    public static ExtractableResponse<Response> 회원보노의_회원비밀번호를_수정한다(RequestSpecification spec) {
        Map<String, Object> updateMemberPasswordRequest = Map.of(
                "password", "newpassword123!",
                "reconfirmPassword", "newpassword123!");

        return 회원비밀번호를_수정한다(회원_보노.getId(), updateMemberPasswordRequest, spec);
    }

    public static void 응답코드가_200이고_응답에_id가_존재하며_회원보노의_회원프로필이_조회되는지_검증한다(ExtractableResponse<Response> response) {
        var 회원보노_회원프로필_조회_응답 = 회원프로필을_조회한다(회원_보노.getId(), FAKE_SPEC);

        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getObject("id", Long.class)).isNotNull(),
                () -> 응답코드를_검증한다(회원보노_회원프로필_조회_응답, HttpStatus.OK),
                () -> assertThat(회원보노_회원프로필_조회_응답.jsonPath().getString("email")).isEqualTo(회원_보노.getEmail())
        );
    }

    public static void 응답코드가_200이고_회원보노의_회원프로필이_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getLong("memberId")).isEqualTo(회원_보노.getId()),
                () -> assertThat(response.jsonPath().getString("myImageUrl")).isEqualTo(회원_보노.getMyImageUrl()),
                () -> assertThat(response.jsonPath().getString("nickname")).isEqualTo(회원_보노.getNickname()),
                () -> assertThat(response.jsonPath().getString("email")).isEqualTo(회원_보노.getEmail()),
                () -> assertThat(response.jsonPath().getString("mood")).isEqualTo(회원_보노.getMood())
        );
    }

    public static void 응답코드가_204이고_회원보노의_회원프로필이_조회되지_않는지_검증한다(ExtractableResponse<Response> response) {
        var 회원보노_회원프로필_조회_응답 = 회원프로필을_조회한다(회원_보노.getId(), FAKE_SPEC);

        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.NO_CONTENT)
//                TODO 회원프로필 조회 실패 구현 기능 구현 뒤 실패 케이스 검증 추가
//                () -> 응답코드를_검증한다(회원보노_회원프로필_조회_응답, HttpStatus.NOT_FOUND)
        );
    }

    public static void 응답코드가_204이고_회원보노의_닉네임이_보노보노로_수정되었는지_검증한다(ExtractableResponse<Response> response) {
//        var 보노_회원프로필_조회_응답 = 회원프로필을_조회한다(회원_보노.getId(), FAKE_SPEC);

        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.NO_CONTENT)
//                TODO 회원 프로필 수정 로직 구현 뒤 회원 프로필 검증 추가
//                () -> assertThat(보노_회원프로필_조회_응답.jsonPath().getString("nickname")).isEqualTo("보노보노")
        );
    }

    public static void 응답코드가_204이고_회원보노가_수정_전의_비밀번호로_로그인에_실패하는지_검증한다(ExtractableResponse<Response> response) {
//        TODO 로그인 시 패스워드 불일치 검증 기능 구현 뒤 실패 케이스 검증 추가
//        var bonoLoginByWrongPasswordResponse = 회원보노가_잘못된_비밀번호를_입력하고_로그인한다(FAKE_SPEC);

        Assertions.assertAll(
                () -> 응답코드를_검증한다(response, HttpStatus.NO_CONTENT)
//                () -> 응답코드를_검증한다(bonoLoginByWrongPasswordResponse, HttpStatus.UNAUTHORIZED)
        );
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

    private static AbstractIntegerAssert<?> 응답코드를_검증한다(ExtractableResponse<Response> response,
            HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }
}
