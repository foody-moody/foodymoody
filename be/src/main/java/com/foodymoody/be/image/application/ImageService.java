package com.foodymoody.be.image.application;

import com.foodymoody.be.common.exception.ImageNotFoundException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.domain.ImageRepository;
import com.foodymoody.be.image.presentation.response.ImageUploadResponse;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.domain.ImageResource;
import com.foodymoody.be.image.domain.ImageStorage;
import com.foodymoody.be.image.domain.ImageMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageStorage imageStorage;
    private final ImageRepository imageRepository;

    public ImageUploadResponse save(ImageCategory category, String resourceId, MultipartFile file) {
        MemberId memberId = IdFactory.createMemberId(resourceId);
        ImageResource imageResource = ImageMapper.toImageResource(file);
        String uuid = UUID.randomUUID().toString();
        String key = imageStorage.generateKey(category, resourceId, uuid, imageResource.getFilename());
        String storageUrl = imageStorage.upload(key, imageResource);
        Image savedImage = imageRepository.save(new Image(IdFactory.createImageId(), storageUrl, memberId));
        return ImageMapper.toUploadResponse(savedImage);
    }

    public void delete(String memberIdValue, String id) {
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
        Image image = findById(IdFactory.createImageId(id));
        image.validateIsUploader(memberId);
        String key = imageStorage.getKey(image.getUrl());
        imageStorage.delete(key);
        imageRepository.delete(image);
    }

    @Transactional(readOnly = true)
    public Image findById(ImageId id) {
        return imageRepository.findById(id)
                .orElseThrow(ImageNotFoundException::new);
    }

}

