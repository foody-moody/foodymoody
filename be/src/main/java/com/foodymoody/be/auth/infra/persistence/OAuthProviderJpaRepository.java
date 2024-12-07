package com.foodymoody.be.auth.infra.persistence;

import com.foodymoody.be.auth.domain.OAuthProvider;
import com.foodymoody.be.common.auth.AuthProvider;
import com.foodymoody.be.common.auth.SupportedAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OAuthProviderJpaRepository extends JpaRepository<OAuthProvider, SupportedAuthProvider> {

    OAuthProvider findByName(AuthProvider authProvider);

}
