package com.foodymoody.be.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private long accessTokenExp;
    private long refreshTokenExp;
    private String issuer;
    private SecretKey secretKey;
    private ClaimUtil claimUtil;

    public JwtUtil(
            @Value("${jwt.token.exp.access}") long accessTokenExp,
            @Value("${jwt.token.exp.refresh}") long refreshTokenExp,
            @Value("${jwt.token.secret}") String secret,
            @Value("${jwt.token.issuer}") String issuer,
            ClaimUtil claimUtil) {
        this.accessTokenExp = accessTokenExp;
        this.refreshTokenExp = refreshTokenExp;
        this.issuer = issuer;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.claimUtil = claimUtil;
    }

    public String createAccessToken(Date now, String id, String email) {
        Map<String, Object> idClaim = claimUtil.createClaim("id", id);
        Map<String, Object> emailClaim = claimUtil.createClaim("email", email);
        return createToken(now, accessTokenExp, issuer, secretKey, idClaim, emailClaim);
    }

    public String createRefreshToken(Date now, String id) {
        Map<String, Object> idClaim = claimUtil.createClaim("id", id);
        return createToken(now, refreshTokenExp, issuer, secretKey, idClaim);
    }

    public Map<String, String> parseAccessToken(String token) {
        Claims claims = claimUtil.extractClaims(token);
        String id = claimUtil.getClaim(claims, "id", String.class);
        String email = claimUtil.getClaim(claims, "email", String.class);
        return Map.of("id", id, "email", email);
    }

    @SafeVarargs
    private String createToken(Date now, long exp, String issuer, SecretKey secretKey, Map<String, Object>... claims) {
        Date expiration = new Date(now.getTime() + exp);

        JwtBuilder builder = Jwts.builder();
        Arrays.stream(claims).forEach(builder::addClaims);

        return builder.setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();
    }
}
