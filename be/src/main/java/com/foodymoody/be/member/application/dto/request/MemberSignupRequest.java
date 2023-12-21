package com.foodymoody.be.member.application.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberSignupRequest {

    // TODO 닉네임 형식 검증
    @NotBlank(message = "닉네임은 공백일 수 없습니다")
    private String nickname;
    @Email(message = "올바른 형식의 이메일을 입력해주세요")
    @NotBlank(message = "이메일은 공백일 수 없습니다")
    private String email;
    @NotBlank(message = "패스워드는 공백일 수 없습니다")
    private String password;
    private String reconfirmPassword;
    @NotBlank(message = "올바른 무드를 입력해주세요")
    private String tasteMoodId;
    
}
