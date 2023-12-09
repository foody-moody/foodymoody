package com.foodymoody.be.acceptance.member;

import static com.foodymoody.be.acceptance.auth.AuthSteps.로그인_한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_등록한다;
import static com.foodymoody.be.acceptance.feed.FeedSteps.피드를_또_등록한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.닉네임_중복_여부를_조회한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.비밀번호를_수정한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.비회원보노가_유효하지_않은_이메일을_입력하고_닉네임을_입력하지_않고_패스워드를_입력하지_않고_회원가입한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.비회원보노가_틀린_재입력_패스워드로_회원가입한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.비회원보노가_회원가입한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.비회원보노가_회원푸반의_닉네임으로_회원가입한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.비회원보노가_회원푸반의_이메일로_회원가입한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_200이고_빈_리스트를_응답하는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_200이고_응답에_id가_존재하며_회원가입한_보노의_회원프로필이_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_200이고_전체_테이스트_무드가_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_200이고_중복되는_닉네임임을_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_200이고_회원푸반이_작성한_피드목록이_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_400이고_오류코드가_g001이고_errors에_email과_nickname과_password가_존재하는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_400이고_오류코드가_m002인지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_400이고_오류코드가_m003인지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드가_400이고_오류코드가_m004인지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.상태코드를_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.아직_피드를_작성하지_않은_회원아티가_작성한_피드목록을_조회한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.전체_테이스트_무드를_조회한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.테이스트무드를_설정한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.피드목록을_조회한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.회원탈퇴한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.푸반_회원프로필_조회한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.회원푸반이_작성한_피드목록을_조회한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.회원프로필을_조회한다;
import static com.foodymoody.be.member.util.MemberFixture.회원_푸반;
import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.auth.util.JwtUtil;
import com.foodymoody.be.member.util.MemberFixture;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@DisplayName("회원 관련 기능 인수테스트")
class MemberAcceptanceTest extends AcceptanceTest {

    @Autowired
    JwtUtil jwtUtil;

    @Nested
    @DisplayName("회원 가입 인수테스트")
    class SignUp {

        @DisplayName("회원 가입 시 성공하면, 상태코드 200과 id를 반환하고 회원 프로필이 조회된다.")
        @Test
        void when_signupMember_then_response200AndId_and_canFetchMemberProfile() {
            // docs
            api_문서_타이틀("signupMember_success", spec);

            // when
            var response = 비회원보노가_회원가입한다(spec);

            // then
            상태코드가_200이고_응답에_id가_존재하며_회원가입한_보노의_회원프로필이_조회되는지_검증한다(response);
        }

        @DisplayName("회원 가입 시 잘못된 입력값을 입력하면, 상태코드 400과 오류코드 g001를 응답한다")
        @Test
        void when_registerMemberFailedByMultipleInvalidInput_then_response400Andm004() {
            // docs
            api_문서_타이틀("signupMember_failedByMultipleInvalidInput", spec);

            // when
            var response = 비회원보노가_유효하지_않은_이메일을_입력하고_닉네임을_입력하지_않고_패스워드를_입력하지_않고_회원가입한다(spec);

            // then
            상태코드가_400이고_오류코드가_g001이고_errors에_email과_nickname과_password가_존재하는지_검증한다(response);
        }

        @DisplayName("회원 가입 시 이미 가입된 이메일이면, 상태코드 400과 오류코드 m002를 응답한다")
        @Test
        void when_signupMemberFailedByDuplicateEmail_then_response400Andm002() {
            // docs
            api_문서_타이틀("signupMember_failedByDuplicateEmail", spec);

            // when
            var response = 비회원보노가_회원푸반의_이메일로_회원가입한다(spec);

            // then
            상태코드가_400이고_오류코드가_m002인지_검증한다(response);
        }

        @DisplayName("회원 가입 시 이미 가입된 닉네임이면, 상태코드 400과 오류코드 m003를 응답한다")
        @Test
        void when_registerMemberFailedByDuplicateNickname_then_response400Andm003() {
            // docs
            api_문서_타이틀("signupMember_failedByDuplicateNickname", spec);

            // when
            var response = 비회원보노가_회원푸반의_닉네임으로_회원가입한다(spec);

            // then
            상태코드가_400이고_오류코드가_m003인지_검증한다(response);
        }

        @DisplayName("회원 가입 시 재입력한 패스워드가 일치하지 않으면, 상태코드 400과 오류코드 m004를 응답한다")
        @Test
        void when_registerMemberFailedByReconfirmPasswordUnmatch_then_response400Andm004() {
            // docs
            api_문서_타이틀("signupMember_failedByReconfirmPasswordUnmatch", spec);

            // when
            var response = 비회원보노가_틀린_재입력_패스워드로_회원가입한다(spec);

            // then
            상태코드가_400이고_오류코드가_m004인지_검증한다(response);
        }

    }

    @Nested
    @DisplayName("회원이 작성한 피드 목록 조회 인수테스트")
    class fetchProfile {
        @DisplayName("회원이 작성한 피드 목록 조회시 성공하면, 상태코드 200과 회원이 작성한 피드 목록을 응답한다")
        @Test
        void when_fetchMemberFeeds_then_response200AndMemberFeeds() {
            // docs
            api_문서_타이틀("fetchMemberFeeds_success", spec);

            // given
            String 푸반_아이디 = jwtUtil.parseAccessToken(회원푸반_액세스토큰).get("id");
            ExtractableResponse<Response> 첫번째_피드_등록_응답 = 피드를_등록한다(회원푸반_액세스토큰, new RequestSpecBuilder().build());
            ExtractableResponse<Response> 두번째_피드_등록_응답 = 피드를_또_등록한다(회원푸반_액세스토큰, new RequestSpecBuilder().build());

            // when
            var response = 피드목록을_조회한다(푸반_아이디, 0, 10, new RequestSpecBuilder().build());

            // then
            List<Map<String, String>> expected = List.of(
                    Map.of("id", 두번째_피드_등록_응답.jsonPath().getString("id"),
                            "imageUrl", "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png3"),
                    Map.of("id", 첫번째_피드_등록_응답.jsonPath().getString("id"),
                            "imageUrl", "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1")
            );
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.OK),
                    () -> assertThat(response.jsonPath().getList("content"))
                            .usingRecursiveComparison()
                            .isEqualTo(expected)
            );

        }

        @DisplayName("회원이 작성한 피드가 없으면, 상태코드 200과 빈 리스트를 응답한다")
        @Test
        void when_fetchMemberFeedsEmpty_then_response200AndEmptyList() {
            // docs
            api_문서_타이틀("fetchMemberFeedsEmpty_success", spec);

            // when
            var response = 아직_피드를_작성하지_않은_회원아티가_작성한_피드목록을_조회한다(spec);

            // then
            상태코드가_200이고_빈_리스트를_응답하는지_검증한다(response);
        }

    }

    @Nested
    @DisplayName("전체 테이스트 무드 조회 인수테스트")
    class fetchAllTasteMoods {

        @DisplayName("전체 테이스트 무드 조회 시 성공하면, 상태코드 200과 taste 무드를 응답한다")
        @Test
        void when_fetchProfileSuccess_then_response200AndProfile() {
            // docs
            api_문서_타이틀("fetchAllTasteMoods_success", spec);

            // when
            var response = 전체_테이스트_무드를_조회한다(spec);

            // then
            상태코드가_200이고_전체_테이스트_무드가_조회되는지_검증한다(response);
        }

    }

    @Nested
    @DisplayName("닉네임 중복 체크 인수테스트")
    class CheckNicknameDuplication {

        @Test
        void when_nicknameNotDuplicate_then_returnFalse() {
            // docs
            api_문서_타이틀("checkNicknameDuplicate_success", spec);

            // when
            var response = 닉네임_중복_여부를_조회한다("푸반", spec);

            // then
            상태코드가_200이고_중복되는_닉네임임을_검증한다(response);

        }
    }

    @Nested
    @DisplayName("회원 비밀번호 수정 인수테스트")
    class ChangePassword {

        private String 푸반_아이디;

        @BeforeEach
        public void set푸반_아이디() {
            푸반_아이디 = jwtUtil.parseAccessToken(회원푸반_액세스토큰).get("id");

        }

        @Test
        void when_changeMemberPassword_then_success() {
            // docs
            api_문서_타이틀("changeMemberPassword_success", spec);

            // when
            var response = 비밀번호를_수정한다(
                    회원푸반_액세스토큰, 푸반_아이디, MemberFixture.푸반_비밀번호_수정_요청(), spec);

            // then
            String 새로운_비밀번호 = MemberFixture.푸반_비밀번호_수정_요청().getNewPassword();
            ExtractableResponse<Response> 푸반_로그인_응답 = 로그인_한다(회원_푸반.getEmail(), 새로운_비밀번호, new RequestSpecBuilder().build());
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.NO_CONTENT),
                    () -> 상태코드를_검증한다(푸반_로그인_응답, HttpStatus.OK)
            );

        }

        @Test
        void when_changeMemberPasswordWithIncorrectPassword_then_fail() {
            // docs
            api_문서_타이틀("changeMemberPasswordWithIncorrectPassword_fail", spec);

            // when
            var response = 비밀번호를_수정한다(회원푸반_액세스토큰, 푸반_아이디,
                    MemberFixture.푸반_비밀번호_수정_요청_인증_실패(), spec);

            // then
            상태코드를_검증한다(response, HttpStatus.UNAUTHORIZED);
        }

        @Test
        void when_changeMemberPasswordWithNotMatchPattern_then_fail() {
            // docs
            api_문서_타이틀("changeMemberPasswordWithNotMatchPattern_fail", spec);

            // when
            var response = 비밀번호를_수정한다(회원푸반_액세스토큰, 푸반_아이디,
                    MemberFixture.푸반_비밀번호_수정_요청_틀린_형식(), spec);

            // then
            상태코드를_검증한다(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Nested
    @DisplayName("회원 테이스트 무드 수정 인수테스트")
    class setTasteMood {

        private String 푸반_아이디;

        @BeforeEach
        public void set푸반_아이디() {
            푸반_아이디 = jwtUtil.parseAccessToken(회원푸반_액세스토큰).get("id");
        }

        @Test
        void when_setTasteMood_then_success() {
            // docs
            api_문서_타이틀("setTasteMood_success", spec);

            // when
            var response = 테이스트무드를_설정한다(회원푸반_액세스토큰, 푸반_아이디, "3", spec);

            // then

            ExtractableResponse<Response> 푸반_프로필조회_응답 = 회원프로필을_조회한다(푸반_아이디, new RequestSpecBuilder().build());
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.NO_CONTENT),
                    () -> 푸반_프로필조회_응답.jsonPath().getString("tasteMood").equals("3")
            );
        }

        @Test
        void when_setTasteMoodUnauthorized_then_fail() {
            // docs
            api_문서_타이틀("setTasteMoodUnauthorized_fail", spec);

            // when
            var response = 테이스트무드를_설정한다(회원아티_액세스토큰, 푸반_아이디, "3", spec);

            // then
            상태코드를_검증한다(response, HttpStatus.UNAUTHORIZED);
        }

        @Test
        void when_setTasteMoodNotExist_then_fail() {
            // docs
            api_문서_타이틀("setTasteMoodNotExist_fail", spec);

            // when
            var response = 테이스트무드를_설정한다(회원푸반_액세스토큰, 푸반_아이디, "10", spec);

            // then
            상태코드를_검증한다(response, HttpStatus.NOT_FOUND);
        }

    }


    @Nested
    @DisplayName("회원 탈퇴 인수테스트")
    class Delete {

        private String 푸반_아이디;

        @BeforeEach
        public void set푸반_아이디() {
            푸반_아이디 = jwtUtil.parseAccessToken(회원푸반_액세스토큰).get("id");

        }

        @Test
        void when_deleteMember_then_success() {
            // docs
            api_문서_타이틀("deleteMember_success", spec);

            // when
            var response = 회원탈퇴한다(회원푸반_액세스토큰, 푸반_아이디, spec);

            // then
            ExtractableResponse<Response> 탈퇴한_푸반_로그인_응답 = 로그인_한다(회원_푸반.getEmail(), 회원_푸반.getPassword(), new RequestSpecBuilder().build());
            Assertions.assertAll(
                    () -> 상태코드를_검증한다(response, HttpStatus.NO_CONTENT),
                    () -> 상태코드를_검증한다(탈퇴한_푸반_로그인_응답, HttpStatus.NOT_FOUND)
            );
        }

        @Test
        void when_deleteMemberUnauthorized_then_fail() {
            // docs
            api_문서_타이틀("deleteMemberUnauthorized_fail", spec);

            // when
            var response = 회원탈퇴한다(회원아티_액세스토큰, 푸반_아이디, spec);

            // then
            상태코드를_검증한다(response, HttpStatus.UNAUTHORIZED);
        }

    }

//
//    @DisplayName("회원 프로필 수정 성공하면, 응답코드 204를 반환하고 수정된 회원프로필이 조회된다.")
//    @Test
//    void when_updateMemberProfile_then_response204_and_fetchUpdatedMemberProfile() {
//        // docs
//        api_문서_타이틀("updateMemberProfile", spec);
//
//        // given
//        회원보노가_회원가입한다(FAKE_SPEC);
//
//        // when
//        var response = 회원보노가_닉네임을_보노보노로_수정한다(spec);
//
//        // then
//        응답코드가_204이고_회원보노의_닉네임이_보노보노로_수정되었는지_검증한다(response);
//    }

}
