package com.foodymoody.be.member.application.dto.request;

import com.foodymoody.be.member.domain.PasswordMatch;
import com.foodymoody.be.member.domain.PasswordPattern;
import lombok.Getter;

@Getter
@PasswordMatch
public class ChangePasswordRequest {

    private String oldPassword;

    @PasswordPattern
    private String password;
    private String repeatPassword;

}
