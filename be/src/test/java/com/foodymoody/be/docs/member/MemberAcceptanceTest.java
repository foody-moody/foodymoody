package com.foodymoody.be.docs.member;

import static com.foodymoody.be.docs.member.MemberSteps.보노가_닉네임을_보노보노로_수정한다;
import static com.foodymoody.be.docs.member.MemberSteps.보노가_회원가입한다;
import static com.foodymoody.be.docs.member.MemberSteps.보노가_회원탈퇴한다;
import static com.foodymoody.be.docs.member.MemberSteps.보노의_회원_비밀번호를_수정한다;
import static com.foodymoody.be.docs.member.MemberSteps.보노의_회원프로필을_조회한다;
import static com.foodymoody.be.docs.member.MemberSteps.응답코드가_200이고_보노의_회원프로필이_조회되는지_검증한다;
import static com.foodymoody.be.docs.member.MemberSteps.응답코드가_200이고_보노의_회원프로필이_조회되면_정상적으로_등록된_회원;
import static com.foodymoody.be.docs.member.MemberSteps.응답코드가_204이고_보노의_닉네임이_보노보노로_수정되었는지_검증한다;
import static com.foodymoody.be.docs.member.MemberSteps.응답코드가_204이고_보노의_회원프로필이_조회되지_않는지_검증한다;
import static com.foodymoody.be.docs.member.MemberSteps.응답코드가_204이고_수정_전의_비밀번호로_로그인에_실패하는지_검증한다;

import com.foodymoody.be.docs.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberAcceptanceTest extends Document {

    @DisplayName("회원 가입 성공하면 응답코드 200을 반환한다.")
    @Test
    void when_registerMember_then_response200() {
        // docs
        api_문서_타이틀("registerMember", spec);

        // given, when
        var response = 보노가_회원가입한다(spec);

        // then
        응답코드가_200이고_보노의_회원프로필이_조회되면_정상적으로_등록된_회원(response);
    }

    @DisplayName("회원 탈퇴 성공하면 응답코드 204를 반환한다.")
    @Test
    void when_deleteMember_then_response204() {
        // docs
        api_문서_타이틀("deleteMember", spec);

        // given
        보노가_회원가입한다();

        // when
        var response = 보노가_회원탈퇴한다(spec);

        // then
        응답코드가_204이고_보노의_회원프로필이_조회되지_않는지_검증한다(response);
    }

    @DisplayName("회원 프로필 수정 성공하면 응답코드 204를 반환한다.")
    @Test
    void when_updateMemberProfile_then_response204() {
        // docs
        api_문서_타이틀("updateMemberProfile", spec);

        // given
        보노가_회원가입한다();

        // when
        var response = 보노가_닉네임을_보노보노로_수정한다(spec);

        // then
        응답코드가_204이고_보노의_닉네임이_보노보노로_수정되었는지_검증한다(response);
    }

    @DisplayName("회원 비밀번호 수정 성공하면 응답코드 204를 반환한다.")
    @Test
    void when_updateMemberPassword_then_response204() {
        // docs
        api_문서_타이틀("updateMemberPassword", spec);

        // given, when
        var response = 보노의_회원_비밀번호를_수정한다(spec);

        // then
        응답코드가_204이고_수정_전의_비밀번호로_로그인에_실패하는지_검증한다(response);
    }

    @DisplayName("회원 프로필 조회 성공하면 응답코드 200과 회원 정보를 반환한다.")
    @Test
    void when_loadMemberProfile_then_response200AndProfile() {
        // docs
        api_문서_타이틀("loadMemberProfile", spec);

        // given
        보노가_회원가입한다();

        // when
        var response = 보노의_회원프로필을_조회한다(spec);

        // then
        응답코드가_200이고_보노의_회원프로필이_조회되는지_검증한다(response);
    }
}
