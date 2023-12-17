package com.foodymoody.be.image.domain;

import com.foodymoody.be.image.presentation.response.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public class ImageMapper {

    private ImageMapper() {
        throw new AssertionError("인스턴스화 불가능");
    }

    public static ImageUploadResponse toUploadResponse(Image image) {
        return new ImageUploadResponse(image.getId().getValue(), image.getUrl());
    }

    public static ImageResource toImageResource(MultipartFile file) {
        return new ImageResource(file);
    }
}


