package com.foodymoody.be.utils;

import com.foodymoody.be.auth.application.service.OAuthClientManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthClientManagerConfig {

    @Bean
    public OAuthClientManager oAuthClientManager() {
        return new FakeAuthClientManager();
    }

}
