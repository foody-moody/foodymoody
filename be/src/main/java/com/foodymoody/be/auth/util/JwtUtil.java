package com.foodymoody.be.auth.util;

import com.foodymoody.be.common.exception.InvalidAccessTokenException;
import com.foodymoody.be.common.exception.InvalidTokenException;
import com.foodymoody.be.common.exception.ClaimNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.time.Instant;
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
    private String secret;
    private String issuer;
    private SecretKey secretKey;
    private JwtParser parser;

    public JwtUtil(
            @Value("${jwt.token.exp.access}") long accessTokenExp,
            @Value("${jwt.token.exp.refresh}")long refreshTokenExp,
            @Value("${jwt.token.secret}") String secret,
            @Value("${jwt.token.issuer}") String issuer) {
        this.accessTokenExp = accessTokenExp;
        this.refreshTokenExp = refreshTokenExp;
        this.secret = secret;
        this.issuer = issuer;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public String createAccessToken(String id, String email) {
        Map<String, Object> idClaim = createClaim("id", id);
        Map<String, Object> emailClaim = createClaim("email", email);
        Date now = Date.from(Instant.now());
        return createToken(now, accessTokenExp, issuer, secretKey, idClaim, emailClaim);
    }

    public String createRefreshToken(String id) {
        Map<String, Object> idClaim = createClaim("id", id);
        Date now = Date.from(Instant.now());
        return createToken(now, refreshTokenExp, issuer, secretKey, idClaim);
    }

    public String extractEmail(String token) {
        return extractClaim(token, "email", String.class);
    }

    public String extractId(String token) {
        return extractClaim(token, "id", String.class);
    }

    public void validateAccessToken(String token) {
        Claims claims = extractClaims(token);
        if (Objects.isNull(claims.get("id", String.class))
                || Objects.isNull(claims.get("email", String.class))) {
            throw new InvalidAccessTokenException();
        }
    }

    @SafeVarargs
    private String createToken(Date now, long exp, String issuer, SecretKey secretKey, Map<String, Object>... claims) {
        Date expiration = new Date(now.getTime() + exp);

        JwtBuilder builder = Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(expiration);
        Arrays.stream(claims).forEach(builder::addClaims);

        return builder.signWith(secretKey, SignatureAlgorithm.HS256).compact();
    }

    private Map<String, Object> createClaim(String key, String value) {
        return Map.of(key, value);
    }

    private <T> T extractClaim(String token, String key, Class<T> type) {
        Claims claims = extractClaims(token);
        Object claim = claims.get(key);
        if (Objects.isNull(claim)) {
            throw new ClaimNotFoundException();
        }
        return claims.get(key, type);
    }

    private Claims extractClaims(String token) {
        try {
            return parser.parseClaimsJws(token).getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | ExpiredJwtException exception) {
            throw new InvalidTokenException();
        }
    }
}
