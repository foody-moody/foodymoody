package com.foodymoody.be.image.service;

import com.foodymoody.be.common.exception.ImageNotFoundException;
import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.image.controller.ImageUploadResponse;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.repository.ImageRepository;
import com.foodymoody.be.image.repository.ImageStorage;
import com.foodymoody.be.image.util.ImageMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {

    private final ImageStorage imageStorage;
    private final ImageRepository imageRepository;

    @Transactional
    public ImageUploadResponse save(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String url = imageStorage.upload(file, uuid);
        String id = IdGenerator.generate();
        Image saved = imageRepository.save(Image.of(id, url));
        return ImageMapper.toUploadResponse(saved);
    }

    public Image findBy(String imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(ImageNotFoundException::new);
    }

    @Transactional
    public void delete(Image image) {
        String key = image.getUrl().split("\\/")[3];
        imageStorage.delete(key);
        imageRepository.delete(image);
    }

}

