package com.foodymoody.be.auth.application.usecase;

import com.foodymoody.be.auth.application.dto.request.LoginRequest;
import com.foodymoody.be.auth.application.dto.response.TokenIssueResponse;
import com.foodymoody.be.auth.application.service.TokenService;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.member.domain.Member;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LocalLoginUseCase {

    private final TokenService tokenService;
    private final MemberReadService memberReadService;

    @Transactional
    public TokenIssueResponse login(LoginRequest request) {
        Member member = memberReadService.findByEmail(request.getEmail())
                .orElseThrow(MemberNotFoundException::new);
        member.checkPasswordMatch(request.getPassword());
        Date now = new Date();
        return tokenService.issue(now, member);
    }

}
