package com.foodymoody.be.image.util;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.image.controller.ImageUploadResponse;
import com.foodymoody.be.image.domain.Image;
import java.util.List;
import java.util.stream.Collectors;

public class ImageMapper {

    private ImageMapper() {
        throw new AssertionError("인스턴스화 불가능");
    }

    public static ImageUploadResponse toUploadResponse(Image image) {
        return new ImageUploadResponse(image.getId(), image.getUrl());
    }
}


