package com.foodymoody.be.image.service;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.image.controller.ImageUploadResponse;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.repository.ImageRepository;
import com.foodymoody.be.image.repository.ImageStorage;
import com.foodymoody.be.image.util.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageStorage imageStorage;
    private final ImageRepository imageRepository;

    public ImageUploadResponse upload(MultipartFile file) {
        String url = imageStorage.upload(file);
        String id = IdGenerator.generate();
        Image saved = imageRepository.save(Image.of(id, url));
        return ImageMapper.toUploadResponse(saved);
    }

    public Image findBy(String imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("이미지를 찾을 수 없습니다."));
    }

}

