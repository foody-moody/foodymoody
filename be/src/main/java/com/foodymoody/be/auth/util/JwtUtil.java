package com.foodymoody.be.auth.util;

import com.foodymoody.be.common.exception.UnsupportedClaimException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
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
    private String secret;
    private String issuer;
    private SecretKey secretKey;

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
    }

    public String createAccessToken(String id, String email) {
        Map<String, Object> idClaim = createClaim("id", id);
        Map<String, Object> emailClaim = createClaim("email", email);
        return createToken(accessTokenExp, issuer, secretKey, idClaim, emailClaim);
    }

    public String createRefreshToken(String id) {
        Map<String, Object> idClaim = createClaim("id", id);
        return createToken(refreshTokenExp, issuer, secretKey, idClaim);
    }

    public String extractEmail(String token) {
        return String.valueOf(extractClaim(token, "email"));
    }

    public String extractId(String token) {
        return String.valueOf(extractClaim(token, "id"));
    }

    @SafeVarargs
    private String createToken(long exp, String issuer, SecretKey secretKey, Map<String, Object>... claims) {
        Date now = new Date();
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

    private Object extractClaim(String token, String key) {
        Claims claims = extractClaims(token);
        Object claim = claims.get(key);
        if (Objects.isNull(claim)) {
            throw new UnsupportedClaimException();
        }
        return claim;
    }

    private Claims extractClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        return parser.parseClaimsJws(token).getBody();
    }
}
