package com.foodymoody.be.auth.controller.dto;

import lombok.Getter;

@Getter
public class TokenIssueRequest {

    private String refreshToken;

}
