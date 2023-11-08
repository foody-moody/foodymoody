package com.foodymoody.be.auth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RefreshToken {

    @Id
    String memberId;
    String token;

    private RefreshToken(String memberId, String token) {
        this.memberId = memberId;
        this.token = token;
    }

    public RefreshToken() {

    }

    public static RefreshToken of(String memberId, String refreshToken) {
        return new RefreshToken(memberId, refreshToken);
    }
}
