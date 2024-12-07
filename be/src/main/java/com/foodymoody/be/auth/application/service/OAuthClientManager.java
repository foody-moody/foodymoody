package com.foodymoody.be.auth.application.service;

import com.foodymoody.be.auth.infra.client.OAuthClient;
import com.foodymoody.be.common.auth.AuthProvider;

public interface OAuthClientManager {

    OAuthClient getByProviderType(AuthProvider providerType);

}
