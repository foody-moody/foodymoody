package com.foodymoody.be.auth.controller.dto;

import lombok.Getter;

@Getter
public class LoginRequest {

    private String email;
    private String password;
}
