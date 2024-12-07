package com.foodymoody.be.auth.infra.client;

import com.foodymoody.be.auth.application.dto.OAuthMemberDetails;
import com.foodymoody.be.auth.infra.dto.OAuthTokenResponse;

public interface OAuthClient {

    OAuthMemberDetails getOAuthMemberDetails(String accessToken);

    OAuthTokenResponse getOauthToken(String authorizationCode);

}
