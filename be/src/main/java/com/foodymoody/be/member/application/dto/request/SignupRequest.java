package com.foodymoody.be.member.application.dto.request;

import com.foodymoody.be.common.annotation.IdNotBlank;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import com.foodymoody.be.member.domain.PasswordMatch;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Getter
@PasswordMatch
public class SignupRequest {

    @NotBlank(message = "닉네임은 공백일 수 없습니다")
    private String nickname;
    @Email(message = "올바른 형식의 이메일을 입력해주세요")
    @NotBlank(message = "이메일은 공백일 수 없습니다")
    private String email;
    @NotBlank(message = "패스워드는 공백일 수 없습니다")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].{8,16}$", message = "비밀번호는 8글자 이상이어야 합니다")
    private String password;
    private String repeatPassword;
    @IdNotBlank(message = "무드는 공백일 수 없습니다")
    private TasteMoodId tasteMoodId;
    
}
