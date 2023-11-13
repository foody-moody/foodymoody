package com.foodymoody.be.auth.service;

import com.foodymoody.be.auth.controller.dto.LoginRequest;
import com.foodymoody.be.auth.controller.dto.LoginResponse;
import com.foodymoody.be.auth.util.JwtUtil;
import com.foodymoody.be.member.domain.Member;
import com.foodymoody.be.common.exception.IncorrectMemberPasswordException;
import com.foodymoody.be.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;
    private final JwtUtil jwtUtil;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail());
        authenticate(request, member);
        String accessToken = jwtUtil.createAccessToken(member.getId(), member.getEmail());
        String refreshToken = jwtUtil.createRefreshToken(member.getId());
        tokenService.saveRefreshToken(member.getId(), refreshToken);
        return LoginResponse.of(accessToken, refreshToken);
    }

    private void authenticate(LoginRequest request, Member member) {
        if (!member.equalsPassword(request.getPassword())) {
            throw new IncorrectMemberPasswordException();
        }
    }
}
