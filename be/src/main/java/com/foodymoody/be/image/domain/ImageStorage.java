package com.foodymoody.be.image.domain;

import com.foodymoody.be.common.util.ids.BaseId;
import java.util.List;

public interface ImageStorage {

    String upload(String key, ImageResource imageResource);

    boolean deleteAll(List<String> imageKeys);

    void delete(String key);

    String generateKey(ImageCategory category, BaseId resourceId, String uuid, String originalFilename);

    String getKey(String url);
}
