package com.foodymoody.be.image.repository;

import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.domain.ImageResource;

public interface ImageStorage {

    String upload(String key, ImageResource imageResource);

    void delete(String key);

    String generateKey(ImageCategory category, String resourceId, String uuid, String originalFilename);

    String getKey(String url);
}
