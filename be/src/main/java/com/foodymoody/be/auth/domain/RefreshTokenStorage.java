package com.foodymoody.be.auth.domain;

import com.foodymoody.be.common.util.ids.MemberId;

public interface RefreshTokenStorage {

    void saveRefreshToken(MemberId memberId, String refreshToken);

    String findByMemberId(String memberId);

    void addBlacklist(String token, long exp);

    boolean isBlacklist(String token);

    void deleteByMemberId(String memberId);

}
