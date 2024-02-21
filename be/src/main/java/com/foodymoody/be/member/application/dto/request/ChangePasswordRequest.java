package com.foodymoody.be.member.application.dto.request;

import com.foodymoody.be.member.domain.PasswordMatch;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Getter
@PasswordMatch
public class ChangePasswordRequest {

    private String oldPassword;
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].{8,16}$", message = "비밀번호는 8글자 이상이어야 합니다")
    private String password;
    private String repeatPassword;

}
