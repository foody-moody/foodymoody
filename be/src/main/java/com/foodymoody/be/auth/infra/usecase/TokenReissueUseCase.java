package com.foodymoody.be.auth.infra.usecase;

import com.foodymoody.be.auth.application.TokenService;
import com.foodymoody.be.auth.domain.RefreshTokenStorage;
import com.foodymoody.be.auth.infra.JwtUtil;
import com.foodymoody.be.auth.application.dto.request.TokenIssueRequest;
import com.foodymoody.be.auth.application.dto.response.TokenIssueResponse;
import com.foodymoody.be.common.exception.InvalidTokenException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.application.MemberService;
import java.util.Date;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenReissueUseCase {

    private final JwtUtil jwtUtil;
    private final RefreshTokenStorage refreshTokenStorage;
    private final TokenService tokenService;
    private final MemberService memberService;

    public TokenIssueResponse reIssueToken(TokenIssueRequest request) {
        String refreshToken = request.getRefreshToken();
        String memberId = jwtUtil.parseRefreshToken(refreshToken);
        validateRefreshToken(refreshToken, memberId);
        Member member = memberService.findById(IdFactory.createMemberId(memberId));
        Date now = new Date();
        return tokenService.issue(now, member);
    }

    private void validateRefreshToken(String refreshToken, String memberId) {
        String stored = refreshTokenStorage.findByMemberId(memberId);
        if (!Objects.equals(refreshToken, stored)) {
            throw new InvalidTokenException();
        }
    }

}
