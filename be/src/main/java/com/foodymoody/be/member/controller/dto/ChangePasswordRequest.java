package com.foodymoody.be.member.controller.dto;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
