package com.foodymoody.be.utils;

import com.foodymoody.be.auth.application.service.OAuthClientManager;
import com.foodymoody.be.auth.infra.client.OAuthClient;
import com.foodymoody.be.common.auth.AuthProvider;

public class FakeAuthClientManager implements OAuthClientManager {

    @Override
    public OAuthClient getByProviderType(AuthProvider providerType) {
        return new FakeOAuthClient();
    }
}
