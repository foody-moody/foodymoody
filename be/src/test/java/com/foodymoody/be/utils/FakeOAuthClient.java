package com.foodymoody.be.utils;

import com.foodymoody.be.auth.application.dto.OAuthMemberDetails;
import com.foodymoody.be.auth.infra.client.OAuthClient;
import com.foodymoody.be.auth.infra.dto.OAuthTokenResponse;
import java.lang.reflect.Field;

public class FakeOAuthClient implements OAuthClient {

    @Override
    public OAuthMemberDetails getOAuthMemberDetails(String accessToken) {
        return new OAuthMemberDetails("oauth@oauth.com", "oauth", "oAuthImageUrl.com");
    }

    @Override
    public OAuthTokenResponse getOauthToken(String authorizationCode) {
        OAuthTokenResponse oAuthTokenResponse = new OAuthTokenResponse();
        setFieldByReflection(oAuthTokenResponse, "accessToken", "access_token");
        return oAuthTokenResponse;
    }

    private void setFieldByReflection(OAuthTokenResponse instance, String fieldName, String value) {
        try {
            Class<? extends OAuthTokenResponse> oAuthTokenResponseClass = instance.getClass();
            Field accessTokenField = oAuthTokenResponseClass.getDeclaredField(fieldName);
            accessTokenField.setAccessible(true);
            accessTokenField.set(instance, value);
            accessTokenField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
