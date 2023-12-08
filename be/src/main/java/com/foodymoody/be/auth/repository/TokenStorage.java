package com.foodymoody.be.auth.repository;

import com.foodymoody.be.common.util.ids.MemberId;

public interface TokenStorage {

    void saveRefreshToken(MemberId memberId, String refreshToken);

    String findByMemberId(String memberId);

}
