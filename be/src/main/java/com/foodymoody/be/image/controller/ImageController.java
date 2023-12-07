package com.foodymoody.be.image.controller;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.service.FeedImageService;
import com.foodymoody.be.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final FeedImageService feedImageService;

    @PostMapping("/feeds/{feedId}")
    public ResponseEntity<ImageUploadResponse> uploadFeedImage(
            @MemberId String memberId, @PathVariable String feedId,
            @RequestPart MultipartFile file
    ) {
        ImageUploadResponse response = feedImageService.save(ImageCategory.FEED, feedId, memberId, file);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/members")
    public ResponseEntity<ImageUploadResponse> uploadMemberProfileImage(
            @MemberId String memberId,
            @RequestPart MultipartFile file
    ) {
        ImageUploadResponse response = imageService.save(ImageCategory.MEMBER, memberId, file);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@MemberId String memberId, @PathVariable String id) {
        imageService.delete(memberId, id);
        return ResponseEntity.ok().build();
    }
}


