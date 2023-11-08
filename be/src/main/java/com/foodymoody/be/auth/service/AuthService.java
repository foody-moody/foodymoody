package com.foodymoody.be.auth.service;

import com.foodymoody.be.auth.controller.dto.LoginRequest;
import com.foodymoody.be.auth.controller.dto.LoginResponse;
import com.foodymoody.be.member.repository.dto.MemberCredential;
import com.foodymoody.be.common.exception.IncorrectMemberPasswordException;
import com.foodymoody.be.common.exception.MemberNotFoundException;
import com.foodymoody.be.member.repository.MemberRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        MemberCredential credential =
                memberRepository.getCredentialByEmail(request.getEmail())
                .orElseThrow(MemberNotFoundException::new);
        authenticate(request, credential);
        String accessToken = tokenService.createAccessToken(credential.getId(), credential.getEmail());
        String refreshToken = tokenService.createRefreshToken(credential.getId());
        tokenService.saveRefreshToken(credential.getId(), refreshToken);
        return LoginResponse.of(accessToken, refreshToken);
    }

    private void authenticate(LoginRequest request, MemberCredential credential) {
        if (!Objects.equals(request.getPassword(), credential.getPassword())) {
            throw new IncorrectMemberPasswordException();
        }
    }
}
