package com.foodymoody.be.member.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberSignupRequest {

    // TODO 닉네임 형식 검증
    @NotNull(message = "닉네임은 공백일 수 없습니다")
    private String nickname;
    @Email(message = "올바른 형식의 이메일을 입력해주세요") @NotNull(message = "이메일은 공백일 수 없습니다")
    private String email;
    // TODO 패스워드 형식 검증
    @NotNull(message = "패스워드는 공백일 수 없습니다")
    private String password;
    private String reconfirmPassword;
    private String mood;
}
