package com.foodymoody.be.auth.infra.usecase;

import com.foodymoody.be.auth.application.TokenService;
import com.foodymoody.be.auth.application.dto.request.LoginRequest;
import com.foodymoody.be.auth.application.dto.response.TokenIssueResponse;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.member.application.MemberService;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LoginUseCase {

    private final TokenService tokenService;
    private final MemberService memberService;

    @Transactional
    public TokenIssueResponse login(LoginRequest request) {
        Member member = memberService.findByEmail(request.getEmail());
        member.checkPasswordMatch(request.getPassword());
        Date now = new Date();
        return tokenService.issue(now, member);
    }

}
