package com.foodymoody.be.auth.service;

import com.foodymoody.be.auth.domain.RefreshToken;
import com.foodymoody.be.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(String memberId, String refreshToken) {
        refreshTokenRepository.save(RefreshToken.of(memberId, refreshToken));
    }
}
