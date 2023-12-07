package com.foodymoody.be.auth.service;

import com.foodymoody.be.auth.controller.dto.LoginRequest;
import com.foodymoody.be.auth.controller.dto.LoginResponse;
import com.foodymoody.be.auth.util.JwtUtil;
import com.foodymoody.be.common.util.ids.MemberId;
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
    private final JwtUtil jwtUtil;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Member member = memberService.findByEmail(request.getEmail());
        member.validatePassword(request.getPassword());
        Date now = new Date();
        MemberId id = member.getId();
        String accessToken = jwtUtil.createAccessToken(now, id.getValue(), member.getEmail());
        String refreshToken = jwtUtil.createRefreshToken(now, id.getValue());
        tokenService.saveRefreshToken(id.getValue(), refreshToken);
        return LoginResponse.of(accessToken, refreshToken);
    }
}
