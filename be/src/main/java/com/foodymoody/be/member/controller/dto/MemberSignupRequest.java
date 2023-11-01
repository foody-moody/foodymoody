package com.foodymoody.be.member.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberSignupRequest {

    // TODO 닉네임 형식 검증
    @NotNull(message = "닉네임은 공백일 수 없습니다")
    private String nickname;
    @Email
    private String email;
    // TODO 패스워드 형식 검증
    @NotNull(message = "패스워드는 공백일 수 없습니다")
    private String password;
    @NotNull(message = "입력하신 패스워드와 일치하지 않습니다")
    private String reconfirmPassword;
    private String mood;

    public MemberSignupRequest(String nickname, String email, String password, String reconfirmPassword, String mood) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.reconfirmPassword = reconfirmPassword;
        this.mood = mood;
    }
}