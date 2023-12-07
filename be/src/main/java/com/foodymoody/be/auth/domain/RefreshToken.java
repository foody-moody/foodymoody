package com.foodymoody.be.auth.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RefreshToken {

    @Id
    MemberId memberId;
    String token;

    private RefreshToken(MemberId memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    public RefreshToken() {

    }

    public static RefreshToken of(MemberId memberId, String refreshToken) {
        return new RefreshToken(memberId, refreshToken);
    }
}
