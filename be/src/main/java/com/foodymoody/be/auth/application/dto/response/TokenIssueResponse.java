package com.foodymoody.be.auth.application.dto.response;

import lombok.Getter;

@Getter
public class TokenIssueResponse {

    private String accessToken;
    private String refreshToken;

    public TokenIssueResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static TokenIssueResponse of(String accessToken, String refreshToken) {
        return new TokenIssueResponse(accessToken, refreshToken);
    }
}
