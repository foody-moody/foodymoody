package com.foodymoody.be.auth.util;

import com.foodymoody.be.auth.domain.Principal;
import com.foodymoody.be.common.exception.InvalidAccessTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static long accessTokenExp;
    private static long refreshTokenExp;
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.issuer}")
    private String issuer;
    private SecretKey secretKey;

    static {
        accessTokenExp = 3600000;
        refreshTokenExp = 2592000000L;
    }

    @PostConstruct
    public void setSecretKey() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public enum TokenType {

        ACCESS(accessTokenExp), REFRESH(refreshTokenExp);

        private final long exp;

        TokenType(long exp) {
            this.exp = exp;
        }

    }

    public String createToken(TokenType type, Map<String, Object>... claims) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + type.exp);

        JwtBuilder builder = Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secretKey);
        Arrays.stream(claims).forEach(builder::addClaims);

        return builder.compact();
    }

    public Principal extractPrincipal(String accessToken) {
        try {
            Claims claims = extractClaims(accessToken);
            String id = claims.get("id", String.class);
            String email = claims.get("email", String.class);
            return Principal.of(id, email);
        }catch (Exception e) {
            throw new InvalidAccessTokenException();
        }
    }

    public Claims extractClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        return parser.parseClaimsJws(token).getBody();
    }
}
