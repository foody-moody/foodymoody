package com.foodymoody.be.member.util;

import com.foodymoody.be.common.auth.SupportedAuthProvider;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.application.dto.ImageDefaultProfileData;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.domain.MemberProfileImage;
import java.time.LocalDateTime;
import java.util.Map;

public enum MemberFixture {

    사용자_알버트("albert@albert.com", "albert123!", "알버트", "1"),
    사용자_설리("sully@sully.com", "sully123!", "설리", "1"),
    사용자_보노("bono@bono.com", "bono123!", "보노", "1"),
    사용자_아티("ati@ati.com", "atiati123!", "아티", "1"),
    사용자_푸반("puban@puban.com", "puban123!", "푸반", "1");

    private String email;
    private String password;
    private String nickname;
    private String tasteMoodId;

    MemberFixture(String email, String password, String nickname, String tasteMoodId) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.tasteMoodId = tasteMoodId;
    }

    public static Map<String, Object> 푸반_비밀번호_수정_요청() {
        return Map.of(
                "oldPassword", 사용자_푸반.getPassword(),
                "password", "atiati123!",
                "repeatPassword", "atiati123!");
    }

    public static Map<String, Object> 푸반_비밀번호_수정_요청_틀린_형식() {
        return Map.of(
                "oldPassword", 사용자_푸반.getPassword(),
                "password", "puban",
                "repeatPassword", "puban");
    }

    public static Map<String, Object> 푸반_비밀번호_수정_요청_인증_실패() {
        return Map.of(
                "oldPassword", "incorrect123!",
                "password", "atiati123!",
                "repeatPassword", "atiati123!");
    }

    public static Map<String, Object> 보노_프로필_수정_요청(String imageId) {
        return Map.of(
                "nickname", "수정된보노",
                "tasteMoodId", "3",
                "profileImageId", imageId);
    }

    public static Map<String, Object> 보노_프로필_이미지만_수정_요청(String imageId) {
        return Map.of("profileImageId", imageId);
    }

    public static Map<String, Object> 푸반_테이스트_무드만_수정_요청() {
        return Map.of("tasteMoodId", "3");
    }

    public static Map<String, Object> 푸반_닉네임만_수정_요청() {
        return Map.of("nickname", "수정된푸반");
    }

    public static Map<String, Object> 푸반_존재하지_않는_프로필_이미지_수정_요청() {
        return Map.of("profileImageId", "InvalidProfileImageId");
    }

    public static Map<String, Object> 푸반_존재하지_않는_테이스트_무드_수정_요청() {
        return Map.of("tasteMoodId", "100");
    }

    public static Map<String, Object> 푸반_중복된_닉네임_수정_요청() {
        return Map.of("nickname", "보노");
    }

    public static Map<String, Object> 보노_회원가입_요청() {
        return Map.of(
                "nickname", 사용자_보노.getNickname(),
                "email", 사용자_보노.getEmail(),
                "password", 사용자_보노.getPassword(),
                "repeatPassword", 사용자_보노.getPassword(),
                "tasteMoodId", 사용자_보노.getTasteMoodId());
    }

    public static Map<String, Object> 알버트_회원가입_요청() {
        return Map.of(
                "nickname", 사용자_알버트.getNickname(),
                "email", 사용자_알버트.getEmail(),
                "password", 사용자_알버트.getPassword(),
                "repeatPassword", 사용자_알버트.getPassword(),
                "tasteMoodId", 사용자_알버트.getTasteMoodId());
    }

    public static Map<String, Object> 설리_회원가입_요청() {
        return Map.of(
                "nickname", 사용자_설리.getNickname(),
                "email", 사용자_설리.getEmail(),
                "password", 사용자_설리.getPassword(),
                "repeatPassword", 사용자_설리.getPassword(),
                "tasteMoodId", 사용자_설리.getTasteMoodId());
    }

    public static Map<String, Object> 아티_회원가입_요청() {
        return Map.of(
                "nickname", 사용자_아티.getNickname(),
                "email", 사용자_아티.getEmail(),
                "password", 사용자_아티.getPassword(),
                "repeatPassword", 사용자_아티.getPassword(),
                "tasteMoodId", 사용자_아티.getTasteMoodId());
    }

    public static Map<String, Object> 푸반_회원가입_요청() {
        return Map.of(
                "nickname", 사용자_푸반.getNickname(),
                "email", 사용자_푸반.getEmail(),
                "password", 사용자_푸반.getPassword(),
                "repeatPassword", 사용자_푸반.getPassword(),
                "tasteMoodId", 사용자_푸반.getTasteMoodId());
    }

    public static Member 테이스트_무드_없는_회원() {
        return Member.of(
                new MemberId("3"),
                SupportedAuthProvider.GOOGLE,
                사용자_보노.getEmail(),
                사용자_보노.getNickname(),
                사용자_보노.getPassword(),
                MemberProfileImage.of(MemberProfileImage.defaultBasicProfileId, "", new ImageDefaultProfileData()),
                null,
                LocalDateTime.now()
        );
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTasteMoodId() {
        return tasteMoodId;
    }

}
