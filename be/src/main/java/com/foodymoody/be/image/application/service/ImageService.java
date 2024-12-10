package com.foodymoody.be.image.application.service;

import com.foodymoody.be.common.exception.ImageNotFoundException;
import com.foodymoody.be.common.exception.NotFoundDefaultProfileImageException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.application.dto.ImageDefaultProfileData;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.domain.ImageMapper;
import com.foodymoody.be.image.domain.ImageRepository;
import com.foodymoody.be.image.domain.ImageResource;
import com.foodymoody.be.image.domain.ImageStorage;
import com.foodymoody.be.image.presentation.dto.response.ImageUploadResponse;
import com.foodymoody.be.member.domain.MemberProfileImage;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final ImageStorage imageStorage;
    private final ImageRepository imageRepository;

    public ImageDefaultProfileData fetchImageDefaultProfile() {
        Image image = imageRepository.fetchImageDefaultProfile(MemberProfileImage.defaultBasicProfileId)
                .orElseThrow(NotFoundDefaultProfileImageException::new);
        return ImageDefaultProfileData.of(image.getId(), image.getUrl());
    }

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

    public ImageUploadResponse saveOAuthMemberProfileImage(MemberId memberId, String imageUrl) {
        ImageId imageId = IdFactory.createImageId();
        Image image = new Image(imageId, imageUrl, memberId);
        return ImageMapper.toUploadResponse(imageRepository.save(image));
    }

    public void softDelete(MemberId currentMemberId, ImageId id) {
        Image image = findById(id);
        image.softDelete(currentMemberId);
    }

    public void softDelete(MemberId currentMemberId, List<ImageId> ids) {
        List<Image> images = imageRepository.findAllByIdInAndDeletedFalse(ids);
        images.forEach(image -> image.validateIsUploader(currentMemberId));
        imageRepository.setDeletedTrueInBatch(images);
    }

    @Transactional(readOnly = true)
    public Image findById(ImageId id) {
        return imageRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(ImageNotFoundException::new);
    }

    @Async
    @Scheduled(cron = "0 0 0 ? * MON")
    @Transactional
    void hardDelete() {
        List<Image> images = imageRepository.findAllByDeletedTrue();
        List<String> imageKeys = images.stream()
                .map(image -> imageStorage.getKey(image.getUrl()))
                .collect(Collectors.toUnmodifiableList());
        imageStorage.deleteAll(imageKeys);
        imageRepository.deleteAllInBatch(images);
    }

    private Image upload(String key, MemberId uploaderId, ImageResource imageResource) {
        String storageUrl = imageStorage.upload(key, imageResource);
        return imageRepository.save(new Image(IdFactory.createImageId(), storageUrl, uploaderId));
    }

}

