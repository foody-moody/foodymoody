package com.foodymoody.be.auth.infra.persistence;

import com.foodymoody.be.auth.domain.RefreshTokenStorage;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class refreshTokenStorage implements RefreshTokenStorage {

    private final ConcurrentHashMap<String, String> refreshTokens = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> blacklist = new ConcurrentHashMap<>();

    @Override
    public void saveRefreshToken(MemberId memberId, String refreshToken) {
        refreshTokens.put(memberId.getValue(), refreshToken);
    }

    @Override
    public String findByMemberId(String memberId) {
        return refreshTokens.get(memberId);
    }

    @Override
    public void addBlacklist(String token, long exp) {
        blacklist.put(token, exp);
    }

    @Override
    public boolean isBlacklist(String token) {
        return blacklist.containsKey(token);
    }

    @Override
    public void deleteByMemberId(String memberId) {
        refreshTokens.remove(memberId);
    }

}
