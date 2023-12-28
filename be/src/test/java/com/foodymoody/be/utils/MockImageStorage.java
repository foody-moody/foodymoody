package com.foodymoody.be.utils;

import com.foodymoody.be.common.util.ids.BaseId;
import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.domain.ImageResource;
import com.foodymoody.be.image.domain.ImageStorage;
import java.util.List;

public class MockImageStorage implements ImageStorage {

    @Override
    public String upload(String key, ImageResource imageResource) {
        return "https://s3Url/key";
    }

    @Override
    public boolean deleteInBatch(List<String> imageKeys) {
        return false;
    }

    @Override
    public void delete(String key) {
    }

    @Override
    public String generateKey(ImageCategory category, BaseId resourceId, String uuid, String originalFilename) {
        return "key";
    }

    @Override
    public String getKey(String url) {
        return "key";
    }
}
