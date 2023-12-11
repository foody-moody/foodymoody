package com.foodymoody.be.auth.service;

import com.foodymoody.be.auth.controller.dto.TokenIssueResponse;
import com.foodymoody.be.auth.repository.TokenStorage;
import com.foodymoody.be.auth.util.JwtUtil;
import com.foodymoody.be.common.exception.InvalidTokenException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.service.MemberService;
import java.util.Date;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenStorage tokenStorage;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    public TokenIssueResponse issue(Date now, Member member) {
        String accessToken = jwtUtil.createAccessToken(now, member.getId().getValue(), member.getEmail());
        String refreshToken = jwtUtil.createRefreshToken(now, member.getId().getValue());
        tokenStorage.saveRefreshToken(member.getId(), refreshToken);
        return new TokenIssueResponse(accessToken, refreshToken);
    }

    public TokenIssueResponse reIssue(String refreshToken) {
        String memberId = jwtUtil.parseRefreshToken(refreshToken);
        validateRefreshToken(refreshToken, memberId);
        Member member = memberService.findById(IdFactory.createMemberId(memberId));
        Date now = new Date();
        return issue(now, member);
    }

    public void revoke(String token) {
        long tokenExp = jwtUtil.getExp(token);
        tokenStorage.addBlacklist(token, tokenExp);
    }

    public void validateNotBlacklisted(String token) {
        if (tokenStorage.isBlacklist(token)) {
            throw new InvalidTokenException();
        }
    }

    private void validateRefreshToken(String refreshToken, String memberId) {
        String stored = tokenStorage.findByMemberId(memberId);
        if (!Objects.equals(refreshToken, stored)) {
            throw new InvalidTokenException();
        }
    }
}
