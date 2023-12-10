package com.foodymoody.be.member.util;

import com.foodymoody.be.member.controller.dto.ChangePasswordRequest;
import com.foodymoody.be.member.controller.dto.UpdateProfileRequest;
import java.util.Map;

public enum MemberFixture {
    비회원_알버트(null, "albert@albert.com", "testtest123!", "알버트", "1", "https://www.image.com"),
    비회원_설리(null, "sully@sully.com", "testtest123!", "설리", "1", "https://www.image.com"),
    비회원_보노(null, "bono@bono.com", "testtest123!", "보노", "1", "https://www.image.com"),
    회원_아티("1", "ati@ati.com", "ati123!", "아티", "1", null),
    회원_푸반("2", "puban@puban.com", "puban123!", "푸반", "1", null);

    private String id;
    private String email;
    private String password;
    private String nickname;
    private String tasteMoodId;
    private String myImageUrl;

    MemberFixture(String id, String email, String password, String nickname, String tasteMoodId, String myImageUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.tasteMoodId = tasteMoodId;
        this.myImageUrl = myImageUrl;
    }

    public static ChangePasswordRequest 푸반_비밀번호_수정_요청() {
        return new ChangePasswordRequest("puban123!", "atiati123!");
    }

    public static ChangePasswordRequest 푸반_비밀번호_수정_요청_틀린_형식() {
        return new ChangePasswordRequest("puban123!", "puban");
    }

    public static ChangePasswordRequest 푸반_비밀번호_수정_요청_인증_실패() {
        return new ChangePasswordRequest("incorrect123!", "puban123!");
    }

    public static UpdateProfileRequest 보노_프로필_수정_요청(String imageId) {
        return new UpdateProfileRequest(imageId, "3");
    }

    public static UpdateProfileRequest 보노_프로필_이미지만_수정_요청(String imageId) {
        return new UpdateProfileRequest(imageId, null);
    }

    public static UpdateProfileRequest 푸반_테이스트_무드만_수정_요청() {
        return new UpdateProfileRequest(null, "3");
    }

    public static UpdateProfileRequest 푸반_존재하지_않는_프로필_이미지_수정_요청() {
        return new UpdateProfileRequest("100", "2");
    }

    public static UpdateProfileRequest 푸반_존재하지_않는_테이스트_무드_수정_요청() {
        return new UpdateProfileRequest("2", "100");
    }

    public static Map<String, Object> 보노_회원가입_요청() {
        return Map.of(
                "nickname", 비회원_보노.getNickname(),
                "email", 비회원_보노.getEmail(),
                "password", 비회원_보노.getPassword(),
                "reconfirmPassword", 비회원_보노.getPassword(),
                "tasteMoodId", 비회원_보노.getTasteMoodId());
    }

    public String getId() {
        return id;
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

    public String getMyImageUrl() {
        return myImageUrl;
    }
}
