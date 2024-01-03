package com.foodymoody.be.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutUseCase {

    private final TokenService tokenService;

    public void logout(String accessToken) {
        tokenService.revoke(accessToken);
    }
}
