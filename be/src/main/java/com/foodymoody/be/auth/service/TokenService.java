package com.foodymoody.be.auth.service;

import com.foodymoody.be.auth.domain.RefreshToken;
import com.foodymoody.be.auth.repository.RefreshTokenRepository;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(String memberIdValue, String refreshToken) {
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
        refreshTokenRepository.save(RefreshToken.of(memberId, refreshToken));
    }
}
