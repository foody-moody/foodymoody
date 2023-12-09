package com.foodymoody.be.auth.repository;

import com.foodymoody.be.common.util.ids.MemberId;

public interface TokenStorage {

    void saveRefreshToken(MemberId memberId, String refreshToken);

    String findByMemberId(String memberId);

    void addBlacklist(String token, long exp);

    boolean isBlacklist(String token);
}
