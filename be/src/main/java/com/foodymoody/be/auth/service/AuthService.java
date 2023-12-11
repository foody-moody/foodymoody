package com.foodymoody.be.auth.service;

import com.foodymoody.be.auth.controller.dto.TokenIssueRequest;
import com.foodymoody.be.auth.controller.dto.TokenIssueResponse;
import com.foodymoody.be.auth.controller.dto.LoginRequest;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.service.MemberService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final TokenService tokenService;

    @Transactional
    public TokenIssueResponse login(LoginRequest request) {
        Member member = memberService.findByEmail(request.getEmail());
        member.checkPasswordMatch(request.getPassword());
        Date now = new Date();
        return tokenService.issue(now, member);
    }

    @Transactional
    public TokenIssueResponse reIssueToken(TokenIssueRequest request) {
        return tokenService.reIssue(request.getRefreshToken());
    }

    @Transactional
    public void logout(String token) {
        tokenService.revoke(token);
    }
}
