package com.foodymoody.be.acceptance.member;

import static com.foodymoody.be.member.util.MemberFixture.비회원_보노;
import static com.foodymoody.be.member.util.MemberFixture.회원_아티;
import static com.foodymoody.be.member.util.MemberFixture.회원_푸반;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.member.controller.dto.ChangePasswordRequest;
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
    ;

    public static ExtractableResponse<Response> 푸반_회원프로필_조회한다(RequestSpecification spec) {
        return 회원프로필을_조회한다(회원_푸반.getId(), spec);
    }

    public static ExtractableResponse<Response> id가_test인_회원프로필을_조회한다(RequestSpecification spec) {
        return 회원프로필을_조회한다("test", spec);
    }

    public static void 푸반_회원_가입한다() {
        회원가입한다(Map.of("nickname", "푸반", "email", "puban@puban.com", "password", "puban123!", "reconfirmPassword",
                "puban123!", "tasteMoodId", '1'), new RequestSpecBuilder().build()).jsonPath().getString("accessToken");
    }

    public static String 아티_회원_가입한다() {
        return 회원가입한다(
                Map.of("nickname", "아티", "email", "ati@ati.com", "password", "atiati123!", "reconfirmPassword", "atiati123!",
                        "tasteMoodId", '1'), new RequestSpecBuilder().build()).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 비회원보노가_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 비회원_보노.getNickname(),
                "email", 비회원_보노.getEmail(),
                "password", 비회원_보노.getPassword(),
                "reconfirmPassword", 비회원_보노.getPassword(),
                "tasteMoodId", 비회원_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 비회원보노가_회원푸반의_이메일로_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 비회원_보노.getNickname(),
                "email", 회원_푸반.getEmail(),
                "password", 비회원_보노.getPassword(),
                "reconfirmPassword", 비회원_보노.getPassword(),
                "tasteMoodId", 비회원_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 비회원보노가_회원푸반의_닉네임으로_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 회원_푸반.getNickname(),
                "email", 비회원_보노.getEmail(),
                "password", 비회원_보노.getPassword(),
                "reconfirmPassword", 비회원_보노.getPassword(),
                "tasteMoodId", 비회원_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 비회원보노가_틀린_재입력_패스워드로_회원가입한다(RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "nickname", 비회원_보노.getNickname(),
                "email", 비회원_보노.getEmail(),
                "password", 비회원_보노.getPassword(),
                "reconfirmPassword", "diffrentPassword",
                "tasteMoodId", 비회원_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 비회원보노가_유효하지_않은_이메일을_입력하고_닉네임을_입력하지_않고_패스워드를_입력하지_않고_회원가입한다(
            RequestSpecification spec) {
        Map<String, Object> memberRegisterRequest = Map.of(
                "email", "test",
                "tasteMoodId", 비회원_보노.getTasteMoodId());

        return 회원가입한다(memberRegisterRequest, spec);
    }

    public static ExtractableResponse<Response> 회원푸반이_작성한_피드목록을_조회한다(RequestSpecification spec) {
        String 회원푸반_아이디 = 회원_푸반.getId();
        return 피드목록을_조회한다(회원푸반_아이디, 0, 2, spec);
    }

    public static ExtractableResponse<Response> 아직_피드를_작성하지_않은_회원아티가_작성한_피드목록을_조회한다(RequestSpecification spec) {
        String 회원아티_아이디 = 회원_아티.getId();
        return 피드목록을_조회한다(회원아티_아이디, 0, 10, spec);
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

    public static void 상태코드가_400이고_오류코드가_m004인지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "m004")
        );
    }

    public static void 상태코드가_404이고_오류코드가_m001인지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.NOT_FOUND),
                () -> 오류코드를_검증한다(response, "m001")
        );
    }

    public static void 상태코드가_400이고_오류코드가_g001이고_errors에_email과_nickname과_password가_존재하는지_검증한다(
            ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.BAD_REQUEST),
                () -> 오류코드를_검증한다(response, "g001"),
                () -> assertThat(response.jsonPath().getMap("errors")).containsOnlyKeys("email", "nickname", "password")
        );

    }

    public static void 상태코드가_200이고_응답에_id가_존재하며_회원가입한_보노의_회원프로필이_조회되는지_검증한다(ExtractableResponse<Response> response) {
        String 회원보노_아이디 = response.jsonPath().getObject("id", String.class);
        var 회원보노_회원프로필_조회_응답 = 회원프로필을_조회한다(회원보노_아이디, MOCK_SPEC);

        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getObject("id", String.class)).isNotNull(),
                () -> 상태코드를_검증한다(회원보노_회원프로필_조회_응답, HttpStatus.OK),
                () -> assertThat(회원보노_회원프로필_조회_응답.jsonPath().getString("email")).isEqualTo(비회원_보노.getEmail())
        );
    }

    public static void 상태코드가_200이고_회원푸반의_회원프로필을_응답하는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getString("email")).isEqualTo(회원_푸반.getEmail()),
                () -> assertThat(response.jsonPath().getString("nickname")).isEqualTo(회원_푸반.getNickname()),
                () -> assertThat(response.jsonPath().getString("tasteMood")).isEqualTo(회원_푸반.getTasteMoodId())
//              TODO 이미지 검증 추가
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
                () -> assertThat(response.jsonPath().getBoolean("isDuplicate")).isTrue()
        );
    }

    public static void  상태코드가_200이고_중복되는_닉네임이_아님을_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getBoolean("isDuplicate")).isFalse()
        );
    }

    public static String 회원보노가_회원가입하고_아이디를_반환한다(RequestSpecification spec) {
        return 비회원보노가_회원가입한다(spec).jsonPath().getString("id");
    }

    public static ExtractableResponse<Response> 회원보노가_닉네임을_보노보노로_수정한다(RequestSpecification spec) {
        Map<String, Object> updateMemberProfileRequest = Map.of("nickname", "보노보노");
        return 회원프로필을_수정한다(비회원_보노.getId(), updateMemberProfileRequest, spec);
    }


    public static void 응답코드가_200이고_회원보노의_회원프로필이_조회되는지_검증한다(ExtractableResponse<Response> response) {
        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.OK),
                () -> assertThat(response.jsonPath().getString("memberId")).isEqualTo(비회원_보노.getId()),
                () -> assertThat(response.jsonPath().getString("myImageUrl")).isEqualTo(비회원_보노.getMyImageUrl()),
                () -> assertThat(response.jsonPath().getString("nickname")).isEqualTo(비회원_보노.getNickname()),
                () -> assertThat(response.jsonPath().getString("email")).isEqualTo(비회원_보노.getEmail()),
                () -> assertThat(response.jsonPath().getString("mood")).isEqualTo(비회원_보노.getTasteMoodId())
        );
    }

    public static void 응답코드가_204이고_회원보노의_회원프로필이_조회되지_않는지_검증한다(ExtractableResponse<Response> response) {
        var 회원보노_회원프로필_조회_응답 = 회원프로필을_조회한다(비회원_보노.getId(), MOCK_SPEC);

        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.NO_CONTENT)
//                TODO 회원프로필 조회 실패 구현 기능 구현 뒤 실패 케이스 검증 추가
//                () -> 응답코드를_검증한다(회원보노_회원프로필_조회_응답, HttpStatus.NOT_FOUND)
        );
    }

    public static void 응답코드가_204이고_회원보노의_닉네임이_보노보노로_수정되었는지_검증한다(ExtractableResponse<Response> response) {
//        var 보노_회원프로필_조회_응답 = 회원프로필을_조회한다(회원_보노.getId(), FAKE_SPEC);

        Assertions.assertAll(
                () -> 상태코드를_검증한다(response, HttpStatus.NO_CONTENT)
//                TODO 회원 프로필 수정 로직 구현 뒤 회원 프로필 검증 추가
//                () -> assertThat(보노_회원프로필_조회_응답.jsonPath().getString("nickname")).isEqualTo("보노보노")
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
            String memberId,
            ChangePasswordRequest request,
            RequestSpecification spec) {
        return RestAssured.given().log().all()
                .spec(spec)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .auth().oauth2(accessToken)
                .body(request)
                .when()
                .put("/api/members/{id}/password", memberId)
                .then()
                .log().all()
                .extract();
    }

    public static ExtractableResponse<Response> 회원가입한다(Map<String, Object> memberRegisterRequest,
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

    public static ExtractableResponse<Response> 회원프로필을_조회한다(String memberId, RequestSpecification spec) {
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

    public static ExtractableResponse<Response> 테이스트무드를_설정한다(String accessToken, String memberId, String tasteMoodId, RequestSpecification spec) {
        return RestAssured
                .given()
                .log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .params("id", tasteMoodId)
                .when()
                .put("/api/members/{memberId}/taste-mood", memberId)
                .then()
                .log().all()
                .extract();
    }


    public static ExtractableResponse<Response> 회원탈퇴한다(String accessToken, String memberId, RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .auth().oauth2(accessToken)
                .log().all()
                .when()
                .delete("/api/members/{memberId}", memberId)
                .then()
                .log().all()
                .extract();
    }


    private static ExtractableResponse<Response> 회원프로필을_수정한다(
            String memberId,
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

    public static ExtractableResponse<Response> 피드목록을_조회한다(String 회원푸반_아이디, int page, int size,
            RequestSpecification spec) {
        return RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .spec(spec)
                .log().all()
                .params("page", page, "size", size)
                .when()
                .get("/api/members/{memberId}/feeds", 회원푸반_아이디)
                .then()
                .log().all()
                .extract();
    }

    public static AbstractIntegerAssert<?> 상태코드를_검증한다(ExtractableResponse<Response> response,
            HttpStatus expectedHttpStatus) {
        return assertThat(response.statusCode()).isEqualTo(expectedHttpStatus.value());
    }

    private static AbstractStringAssert<?> 오류코드를_검증한다(ExtractableResponse<Response> response, String code) {
        return assertThat(response.jsonPath().getString("code")).isEqualTo(code);
    }
}
