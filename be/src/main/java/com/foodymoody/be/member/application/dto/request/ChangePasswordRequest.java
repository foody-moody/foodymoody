package com.foodymoody.be.member.application.dto.request;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;

}
