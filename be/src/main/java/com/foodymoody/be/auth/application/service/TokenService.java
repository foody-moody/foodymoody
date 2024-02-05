package com.foodymoody.be.auth.application.service;

import com.foodymoody.be.auth.application.dto.response.TokenIssueResponse;
import com.foodymoody.be.auth.domain.RefreshTokenStorage;
import com.foodymoody.be.auth.infra.JwtUtil;
import com.foodymoody.be.common.exception.InvalidTokenException;
import com.foodymoody.be.member.domain.Member;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenStorage refreshTokenStorage;
    private final JwtUtil jwtUtil;

    public TokenIssueResponse issue(Date now, Member member) {
        String accessToken = jwtUtil.createAccessToken(now, member.getId().getValue(), member.getEmail());
        String refreshToken = jwtUtil.createRefreshToken(now, member.getId().getValue());
        refreshTokenStorage.saveRefreshToken(member.getId(), refreshToken);
        return TokenIssueResponse.of(accessToken, refreshToken);
    }

    public void revoke(String token) {
        long tokenExp = jwtUtil.extractExpiresAt(token);
        refreshTokenStorage.addBlacklist(token, tokenExp);
    }

    public void validateNotBlacklisted(String token) {
        if (refreshTokenStorage.isBlacklist(token)) {
            throw new InvalidTokenException();
        }
    }

}
