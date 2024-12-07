package com.foodymoody.be.auth.application.service;

import com.foodymoody.be.auth.infra.client.GoogleClient;
import com.foodymoody.be.auth.infra.client.OAuthClient;
import com.foodymoody.be.common.auth.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OAuthClientManagerImpl implements OAuthClientManager {

    private final GoogleClient googleClient;

    @Override
    public OAuthClient getByProviderType(AuthProvider providerType) {
        // FIXME OAuthClientRepository 구현 후 providerType에 맞는 web client를 반환하도록 수정
        return googleClient;
    }

}
