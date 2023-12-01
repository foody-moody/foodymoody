package com.foodymoody.be.common.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // cpu 코어 수에 따라 적절한 값을 설정한다.
    public static final int CORE_POOL_SIZE = 8;

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    }
}
