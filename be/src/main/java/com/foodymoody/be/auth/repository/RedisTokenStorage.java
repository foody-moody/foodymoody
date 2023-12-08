package com.foodymoody.be.auth.repository;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisTokenStorage implements TokenStorage{

    private static final String REFRESH_PREFIX = "jwt:refresh:";

    private final RedisTemplate<String, String> template;

    @Override
    public void saveRefreshToken(MemberId memberId, String refreshToken) {
        template.opsForValue().set(REFRESH_PREFIX + memberId.getValue(), refreshToken);
    }

    @Override
    public String findByMemberId(String memberId) {
        return template.opsForValue().get(REFRESH_PREFIX + memberId);
    }

}
