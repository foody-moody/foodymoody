package com.foodymoody.be.auth.application.service;

import com.foodymoody.be.auth.application.dto.response.TokenIssueResponse;
import com.foodymoody.be.auth.domain.RefreshTokenStorage;
import com.foodymoody.be.auth.infra.util.JwtUtil;
import com.foodymoody.be.common.exception.InvalidTokenException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.member.domain.Member;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenStorage refreshTokenStorage;
    private final JwtUtil jwtUtil;
    private final MemberReadService memberReadService;

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

    public String issueAccessTokenByRefreshToken(String refreshToken) {
        String memberId = jwtUtil.parseRefreshToken(refreshToken);
        Member member = memberReadService.findById(IdFactory.createMemberId(memberId));
        Date now = new Date();
        return jwtUtil.createAccessToken(now, member.getId().getValue(), member.getEmail());
    }

}
