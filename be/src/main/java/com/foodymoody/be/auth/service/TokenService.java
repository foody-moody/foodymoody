package com.foodymoody.be.auth.service;

import com.foodymoody.be.auth.util.JwtUtil;
import com.foodymoody.be.auth.domain.RefreshToken;
import com.foodymoody.be.auth.repository.RefreshTokenRepository;
import com.foodymoody.be.common.exception.AuthorizationFailedException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public String createAccessToken(String memberId, String memberEmail) {
        return jwtUtil.createAccessToken(memberId, memberEmail);
    }

    public String createRefreshToken(String memberId) {
        return jwtUtil.createRefreshToken(memberId);
    }

    public void saveRefreshToken(String memberId, String refreshToken) {
        refreshTokenRepository.save(RefreshToken.of(memberId, refreshToken));
    }

//    TODO 별도의 클래스로 분리 예정
    public String extractToken(WebRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1];
        if (Objects.isNull(token)) {
            throw new AuthorizationFailedException();
        }
        return token;
    }


    public String extractMemberEmail(WebRequest webRequest) {
        String token = extractToken(webRequest);
        return jwtUtil.extractEmail(token);
    }

    public String extractMemberId(NativeWebRequest webRequest) {
        String token = extractToken(webRequest);
        return jwtUtil.extractId(token);
    }

}
