package com.foodymoody.be.auth.domain;

import com.foodymoody.be.common.auth.AuthProvider;
import com.foodymoody.be.common.auth.SupportedAuthProvider;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class OAuthProvider implements AuthProvider {

    @Id
    @Enumerated(EnumType.STRING)
    private SupportedAuthProvider name;

    @Override
    public String getName() {
        return null;
    }
}
