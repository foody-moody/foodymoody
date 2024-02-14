package com.foodymoody.be.member.application.dto.request;

import com.foodymoody.be.member.domain.PasswordMatch;
import lombok.Getter;

@Getter
@PasswordMatch
public class ChangePasswordRequest {

    private String oldPassword;
    private String password;
    private String repeatPassword;

}
