package com.foodymoody.be.acceptance.member;

import static com.foodymoody.be.acceptance.member.MemberSteps.응답코드가_200이고_응답에_id가_존재하며_회원보노의_회원프로필이_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.응답코드가_200이고_회원보노의_회원프로필이_조회되는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.응답코드가_204이고_회원보노가_수정_전의_비밀번호로_로그인에_실패하는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.응답코드가_204이고_회원보노의_닉네임이_보노보노로_수정되었는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.응답코드가_204이고_회원보노의_회원프로필이_조회되지_않는지_검증한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.회원보노가_닉네임을_보노보노로_수정한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.회원보노가_회원가입한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.회원보노가_회원탈퇴한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.회원보노의_회원비밀번호를_수정한다;
import static com.foodymoody.be.acceptance.member.MemberSteps.회원보노의_회원프로필을_조회한다;

import com.foodymoody.be.acceptance.AcceptanceTest;
import com.foodymoody.be.acceptance.util.DatabaseCleanup;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("회원 관련 기능 인수테스트")
class MemberTest extends AcceptanceTest {

    @Autowired
    private DatabaseCleanup cleanup;

    private static final RequestSpecification FAKE_SPEC;

    static {
        FAKE_SPEC = new RequestSpecBuilder().build();
    }

    @BeforeEach
    @AfterEach
    void clearMemberTable() {
        cleanup.execute();
    }

    @DisplayName("회원 가입 성공하면, 응답코드 200과 id를 반환하고 회원 프로필이 조회된다.")
    @Test
    void when_registerMember_then_response200AndId_and_canLoadMemberProfile() {
        // docs
        api_문서_타이틀("signupMember_success", spec);

        // given, when
        var response = 회원보노가_회원가입한다(spec);

        // then
        응답코드가_200이고_응답에_id가_존재하며_회원보노의_회원프로필이_조회되는지_검증한다(response);
    }
//     FIXME 회원가입시 id가 랜덤으로 생성되므로 이에 맞게 테스트 코드 수정 필요
//    @DisplayName("회원 탈퇴 성공하면, 응답코드 204를 반환하고 회원 프로필이 조회되지 않는다.")
//    @Test
//    void when_deleteMember_then_response204_and_cannotLoadMemberProfile() {
//        // docs
//        api_문서_타이틀("withdrawMember", spec);
//
//        // given
//        회원보노가_회원가입한다(FAKE_SPEC);
//
//        // when
//        var response = 회원보노가_회원탈퇴한다(spec);
//
//        // then
//        응답코드가_204이고_회원보노의_회원프로필이_조회되지_않는지_검증한다(response);
//    }
//
//    @DisplayName("회원 프로필 수정 성공하면, 응답코드 204를 반환하고 수정된 회원프로필이 조회된다.")
//    @Test
//    void when_updateMemberProfile_then_response204_and_loadUpdatedMemberProfile() {
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
//
//    @DisplayName("회원 비밀번호 수정 성공하면, 응답코드 204를 반환하고 수정 전 비밀번호로 로그인에 실패한다.")
//    @Test
//    void when_updateMemberPassword_then_response204_and_failLoginByOriginalPassword() {
//        // docs
//        api_문서_타이틀("updateMemberPassword", spec);
//
//        // given
//        회원보노가_회원가입한다(FAKE_SPEC);
//
//        // when
//        var response = 회원보노의_회원비밀번호를_수정한다(spec);
//
//        // then
//        응답코드가_204이고_회원보노가_수정_전의_비밀번호로_로그인에_실패하는지_검증한다(response);
//    }
//
//    @DisplayName("회원 프로필 조회 성공하면, 응답코드 200과 회원 프로필을 반환한다.")
//    @Test
//    void when_loadMemberProfile_then_response200AndMemberProfile() {
//        // docs
//        api_문서_타이틀("loadMemberProfile", spec);
//
//        // given
//        회원보노가_회원가입한다(FAKE_SPEC);
//
//        // when
//        var response = 회원보노의_회원프로필을_조회한다(spec);
//
//        // then
//        응답코드가_200이고_회원보노의_회원프로필이_조회되는지_검증한다(response);
//    }
}
