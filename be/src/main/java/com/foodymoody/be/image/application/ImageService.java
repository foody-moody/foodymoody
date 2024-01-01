package com.foodymoody.be.image.application;

import com.foodymoody.be.common.exception.ImageNotFoundException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.domain.ImageRepository;
import com.foodymoody.be.image.presentation.dto.response.ImageUploadResponse;
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

    public ImageUploadResponse uploadFeedImage(MemberId currentMemberId, MultipartFile file) {
        ImageResource imageResource = ImageMapper.toImageResource(file);
        String uuid = UUID.randomUUID().toString();
        // FIXME null을 매개변수로 받지 않도록 s3 key 생성 로직 리팩토링
        String key = imageStorage.generateKey(ImageCategory.FEED, null, uuid, imageResource.getFilename());
        return ImageMapper.toUploadResponse(upload(key, currentMemberId, imageResource));
    }

    public ImageUploadResponse uploadMemberImage(MemberId currentMemberId, MultipartFile file) {
        ImageResource imageResource = ImageMapper.toImageResource(file);
        String uuid = UUID.randomUUID().toString();
        String key = imageStorage.generateKey(ImageCategory.MEMBER, currentMemberId, uuid, imageResource.getFilename());
        return ImageMapper.toUploadResponse(upload(key, currentMemberId, imageResource));
    }

    private Image upload(String key, MemberId uploaderId, ImageResource imageResource) {
        String storageUrl = imageStorage.upload(key, imageResource);
        return imageRepository.save(new Image(IdFactory.createImageId(), storageUrl, uploaderId));
    }

    public void delete(MemberId currentMemberId, ImageId id) {
        Image image = findById(id);
        image.validateIsUploader(currentMemberId);
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
