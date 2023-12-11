package com.foodymoody.be.auth.controller.dto;

import lombok.Getter;

@Getter
public class TokenIssueResponse {

    private String accessToken;
    private String refreshToken;

    public TokenIssueResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
