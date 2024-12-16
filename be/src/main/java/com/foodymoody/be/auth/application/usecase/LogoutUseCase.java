package com.foodymoody.be.auth.application.usecase;

import com.foodymoody.be.auth.application.service.TokenService;
import com.foodymoody.be.auth.domain.RefreshTokenStorage;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutUseCase {

    private final TokenService tokenService;
    private final RefreshTokenStorage refreshTokenStorage;

    public void logout(String accessToken) {
        tokenService.revoke(accessToken);
    }

    public void logout(MemberId memberId) {
        String accessToken = tokenService.issueAccessTokenByMemberId(memberId);
        tokenService.revoke(accessToken);
    }

}
