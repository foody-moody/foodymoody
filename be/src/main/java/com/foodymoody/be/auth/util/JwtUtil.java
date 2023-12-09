package com.foodymoody.be.auth.util;

import com.foodymoody.be.common.exception.ClaimNotFoundException;
import com.foodymoody.be.common.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private long accessTokenExp;
    private long refreshTokenExp;
    private String issuer;
    private SecretKey secretKey;

    public JwtUtil(
            @Value("${jwt.token.exp.access}") long accessTokenExp,
            @Value("${jwt.token.exp.refresh}") long refreshTokenExp,
            @Value("${jwt.token.secret}") String secret,
            @Value("${jwt.token.issuer}") String issuer) {
        this.accessTokenExp = accessTokenExp;
        this.refreshTokenExp = refreshTokenExp;
        this.issuer = issuer;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createAccessToken(Date now, String id, String email) {
        Map<String, Object> idClaim = createClaim("id", id);
        Map<String, Object> emailClaim = createClaim("email", email);
        return createToken(now, accessTokenExp, issuer, secretKey, idClaim, emailClaim);
    }

    public String createRefreshToken(Date now, String id) {
        Map<String, Object> idClaim = createClaim("id", id);
        return createToken(now, refreshTokenExp, issuer, secretKey, idClaim);
    }

    public Map<String, String> parseAccessToken(String token) {
        Claims claims = extractClaims(token);
        String id = getClaim(claims, "id", String.class);
        String email = getClaim(claims, "email", String.class);
        return Map.of("id", id, "email", email);
    }

    public String parseRefreshToken(String refreshToken) {
        Claims claims = extractClaims(refreshToken);
        return getClaim(claims, "id", String.class);
    }

    public long getExp(String token) {
        Claims claims = extractClaims(token);
        return getClaim(claims, "exp", Long.class);
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

    private Map<String, Object> createClaim(String key, String value) {
        return Map.of(key, value);
    }

    private <T> T getClaim(Claims claims, String key, Class<T> type) {
        try {
            T claim = claims.get(key, type);
            if (Objects.isNull(claim)) {
                throw new ClaimNotFoundException();
            }
            return claim;
        } catch (RequiredTypeException | ClassCastException e) {
            throw new IncorrectClaimException(null, claims, key);
        }
    }

    private Claims extractClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        try {
            return parser.parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            throw new InvalidTokenException();
        }
    }
}
