package com.foodymoody.be.utils;

import com.foodymoody.be.image.repository.ImageStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageStorageConfig {

    @Bean
    public ImageStorage imageStorage() {
        return new MockImageStorage();
    }
}
