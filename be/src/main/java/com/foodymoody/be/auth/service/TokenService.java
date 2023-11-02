package com.foodymoody.be.auth.service;

import com.foodymoody.be.common.exception.InvalidTokenException;
import com.foodymoody.be.auth.util.JwtUtil;
import com.foodymoody.be.auth.util.JwtUtil.TokenType;
import com.foodymoody.be.auth.domain.RefreshToken;
import com.foodymoody.be.auth.repository.RefreshTokenRepository;
import com.foodymoody.be.auth.domain.Principal;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
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
        Map<String, Object> idClaim = createClaim("id", memberId);
        Map<String, Object> emailClaim = createClaim("email", memberEmail);
        return jwtUtil.createToken(TokenType.ACCESS, idClaim, emailClaim);
    }

    public String createRefreshToken(String memberId) {
        Map<String, Object> idClaim = createClaim("id", memberId);
        return jwtUtil.createToken(TokenType.REFRESH, idClaim);
    }

    public void saveRefreshToken(String memberId, String refreshToken) {
        refreshTokenRepository.save(RefreshToken.of(memberId, refreshToken));
    }

    public Principal extractPrincipal(String accessToken) {
        return jwtUtil.extractPrincipal(accessToken);
    }

//    TODO
//    public String extractToken(Object request) {
//        WebRequest webRequest = RequestMapper.toWebRequest(request);
//        return webRequest.getHeader("Authorization").split(" ")[1];
//    }

    public String extractToken(WebRequest request) {
        return request.getHeader("Authorization").split(" ")[1];
    }

    public String extractToken(ServletRequest request) {
        HttpServletRequest casted = (HttpServletRequest) request;
        return casted.getHeader("Authorization").split(" ")[1];
    }

    public void validate(String token) {
        try {
            jwtUtil.extractClaims(token);
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

//    FIXME 빠른 구현을 위해 기존에 구현한 메서드를 가져다 쓰느라 불필요한 로직이 있습니다. 추후 리팩토링하겠습니다.
    public String extractMemberEmail(WebRequest webRequest) {
        String token = extractToken(webRequest);
        return extractPrincipal(token).getEmail();
    }

//    FIXME 빠른 구현을 위해 기존에 구현한 메서드를 가져다 쓰느라 불필요한 로직이 있습니다. 추후 리팩토링하겠습니다.
    public String extractMemberId(NativeWebRequest webRequest) {
        String token = extractToken(webRequest);
        return extractPrincipal(token).getId();
    }


    private Map<String, Object> createClaim(String key, Object value) {
        return Map.of(key, value);
    }
}
