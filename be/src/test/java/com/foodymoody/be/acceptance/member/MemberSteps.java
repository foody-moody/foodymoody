package com.foodymoody.be.acceptance.member;

import static com.foodymoody.be.member.util.MemberFixture.사용자_보노;
import static com.foodymoody.be.member.util.MemberFixture.사용자_푸반;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.member.util.MemberFixture;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MemberSteps {

    private static final RequestSpecification MOCK_SPEC = new RequestSpecBuilder().build();

    public static void 푸반_회원가입한다() {
        회원가입한다(MemberFixture.푸반_회원가입_요청(), new RequestSpecBuilder().build()).jsonPath().getString("accessToken");
    }

    public static String 아티_회원가입한다() {
        return 회원가입한다(MemberFixture.아티_회원가입_요청(), new RequestSpecBuilder().build()).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 비회원보노가_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 사용자_보노.getNickname(),
                "email", 사용자_보노.getEmail(),
                "password", 사용자_보노.getPassword(),
                "repeatPassword", 사용자_보노.getPassword(),
                "tasteMoodId", 사용자_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 비회원보노가_회원푸반의_이메일로_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 사용자_보노.getNickname(),
                "email", 사용자_푸반.getEmail(),
                "password", 사용자_보노.getPassword(),
                "repeatPassword", 사용자_보노.getPassword(),
                "tasteMoodId", 사용자_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 비회원보노가_회원푸반의_닉네임으로_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 사용자_푸반.getNickname(),
                "email", 사용자_보노.getEmail(),
                "password", 사용자_보노.getPassword(),
                "repeatPassword", 사용자_보노.getPassword(),
                "tasteMoodId", 사용자_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 비회원보노가_틀린_재입력_패스워드로_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 사용자_보노.getNickname(),
                "email", 사용자_보노.getEmail(),
                "password", 사용자_보노.getPassword(),
                "repeatPassword", "diffrentPassword",
                "tasteMoodId", 사용자_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 비회원보노가_유효하지_않은_이메일을_입력하고_닉네임을_입력하지_않고_패스워드를_입력하지_않고_회원가입한다(
            RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "email", "test",
                "tasteMoodId", 사용자_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static void 상태코드가_400이고_오류코드가_m002인지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "m002")
        );
    }

    public static void 상태코드가_400이고_오류코드가_m003인지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "m003")
        );
    }

    public static void 상태코드가_400이고_오류코드가_g001인지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "g001")
        );
    }

    public static void 상태코드가_400이고_오류코드가_g001이고_errors에_email과_nickname과_password가_존재하는지_검증한다(
            ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "g001"),
                () -> assertThat(response.jsonPath().getMap("errors")).containsKeys("email", "nickname", "password")
        );

    }

    public static void 상태코드가_201이고_응답에_id가_존재하며_회원가입한_보노의_회원프로필이_조회되는지_검증한다(ExtractableResponse<Response> response) {
        String 회원보노_아이디 = response.jsonPath().getObject("id", String.class);
        var 회원보노_회원프로필_조회_응답 = 회원프로필을_조회한다(회원보노_아이디, MOCK_SPEC);

        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.CREATED),
                () -> assertThat(response.jsonPath().getObject("id", String.class)).isNotNull(),
                () -> 상태코드를_검증한다(회원보노_회원프로필_조회_응답, HttpStatus.OK),
                () -> assertThat(회원보노_회원프로필_조회_응답.jsonPath().getString("email")).isEqualTo(사용자_보노.getEmail())
        );
    }

    public static void 상태코드가_200이고_회원푸반이_작성한_피드목록이_조회되는지_검증한다(ExtractableResponse<Response> response) {
        List<Map<String, String>> expectedResponse = List.of(
                Map.of("id", "1c", "imageUrl",
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1"),
                Map.of("id", "2c", "imageUrl",
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1")
        );
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getList("content"))
                        .usingRecursiveComparison().isEqualTo(expectedResponse),
                () -> assertThat(response.jsonPath().getString("last")).isEqualTo("false")
        );

    }

    public static void 상태코드가_200이고_빈_리스트를_응답하는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getList("content")).isEmpty()
        );
    }

    public static void 상태코드가_200이고_전체_테이스트_무드가_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getList("")).hasSize(6)
        );
    }

    public static void 상태코드가_200이고_중복되는_닉네임임을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getBoolean("duplicate")).isTrue()
        );
    }

    public static ExtractableResponse<Response> 닉네임_중복_여부를_조회한다(String nickname, RequestSpecification spec) {
        return RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .params("nickname", nickname)
                .log().all()
                .when()
                .get("/api/members/duplication-check")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 팔로우한다(String accessToken, String id) {
        return 팔로우한다(accessToken, id, new RequestSpecBuilder().build());
    }

    public static ExtractableResponse<Response> 팔로우한다(String accessToken, String id, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .when()
                .post("/api/members/{id}/followers", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 언팔로우한다(String accessToken, String id, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .when()
                .delete("/api/members/{id}/followers", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 팔로잉_목록을_조회한다(String id, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/members/{id}/followings", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인시_팔로잉_목록을_조회한다(String accessToken, String id, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .when()
                .get("/api/members/{id}/followings", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 팔로워_목록을_조회한다(String id, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/members/{id}/followers", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인시_팔로워_목록을_조회한다(String accessToken, String id, RequestSpecification spec) {
        return RestAssured.given().log().all().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .when()
                .get("/api/members/{id}/followers", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 전체_테이스트_무드를_조회한다(RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .when()
                .get("/api/members/taste-moods")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 비밀번호를_수정한다(String accessToken,
            Map<String, Object> request,
            RequestSpecification spec) {
        return RestAssured.given().log().all()
                .spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .body(request)
                .when()
                .put("/api/members/me/password")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원가입한다(Map<String, Object> memberRegisterRequest, RequestSpecification spec) {
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

    public static ExtractableResponse<Response> 회원프로필을_조회한다(String memberId, RequestSpecification spec) {
        return RestAssured
                .given()
                .spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .log().all()
                .when()
                .get("/api/members/{id}", memberId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 로그인시_회원프로필을_조회한다(String accessToken, String memberId, RequestSpecification spec) {
        return RestAssured
                .given().spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .log().all()
                .when()
                .get("/api/members/{id}", memberId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 테이스트무드를_설정한다(String accessToken, String tasteMoodId, RequestSpecification spec) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .params("id", tasteMoodId)
                .when()
                .put("/api/members/me/taste-mood")
                .then()
                .log().all()
                .extract();
    }


    public static ExtractableResponse<Response> 회원탈퇴한다(String accessToken, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .log().all()
                .when()
                .delete("/api/members/me")
                .then()
                .log().all()
                .extract();
    }


    public static ExtractableResponse<Response> 회원프로필을_수정한다(
            String accessToken,
            Map<String, Object> updateProfileRequest,
            RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .body(updateProfileRequest)
                .log().all()
                .when()
                .patch("/api/members/me")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 피드목록을_조회한다(String memberId, int page, int size, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .params("page", page, "size", size)
                .when()
                .get("/api/members/{id}/feeds", memberId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원이_작성한_피드_컬렉션_목록을_조회한다(String accessToken, String id, int page, int size, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .log().all()
                .params("page", page, "size", size)
                .when()
                .get("/api/members/{id}/collections", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원이_작성한_피드_컬렉션_목록을_조회한다(String id, int page, int size, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .params("page", page, "size", size)
                .when()
                .get("/api/members/{memberId}/collections", id)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원이_작성한_피드_컬렉션_제목_목록을_조회한다(String accessToken,
            RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .log().all()
                .when()
                .get("/api/members/me/collections/titles")
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원이_작성한_피드_컬렉션들의_특정_피드_포함_여부를_조회한다(String accessToken, String feedId,
            RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .log().all()
                .when()
                .get("/api/members/me/collections/with-feed-inclusion-status/{feedId}", feedId)
                .then()
                .log().all()
                .extract();
    }

    public static AbstractIntegerAssert<?> 상태코드를_검증한다(ExtractableResponse<Response> response,
            HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }

    public static AbstractStringAssert<?> 오류코드를_검증한다(ExtractableResponse<Response> response, String code) {
        return assertThat(response.jsonPath().getString("code")).isEqualTo(code);
    }
}
