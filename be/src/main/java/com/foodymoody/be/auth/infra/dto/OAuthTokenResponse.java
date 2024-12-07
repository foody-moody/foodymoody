package com.foodymoody.be.auth.infra.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OAuthTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

}
