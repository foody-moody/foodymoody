package com.foodymoody.be.auth.application.dto.request;

import lombok.Getter;

@Getter
public class TokenIssueRequest {

    private String refreshToken;

}
