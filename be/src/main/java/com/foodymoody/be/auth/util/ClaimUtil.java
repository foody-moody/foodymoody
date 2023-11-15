package com.foodymoody.be.auth.util;

import com.foodymoody.be.common.exception.ClaimNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.security.Keys;
import java.util.Map;
import java.util.Objects;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClaimUtil {

    private String secret;
    private SecretKey secretKey;

    public ClaimUtil(@Value("${jwt.token.secret}") String secret){
        this.secret = secret;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Map<String, Object> createClaim(String key, String value) {
        return Map.of(key, value);
    }

    public <T> T getClaim(Claims claims, String key, Class<T> type) {
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

    public Claims extractClaims(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(secretKey).build();
        return parser.parseClaimsJws(token).getBody();
    }

}
