package com.foodymoody.be.image.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.application.ImageService;
import com.foodymoody.be.image.presentation.response.ImageUploadResponse;
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

    @PostMapping("/feeds")
    public ResponseEntity<ImageUploadResponse> uploadFeedImage(
            @CurrentMemberId MemberId memberId,
            @RequestPart MultipartFile file
    ) {
        ImageUploadResponse response = imageService.uploadFeedImage(memberId, file);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/members")
    public ResponseEntity<ImageUploadResponse> uploadMemberProfileImage(
            @CurrentMemberId MemberId memberId,
            @RequestPart MultipartFile file
    ) {
        ImageUploadResponse response = imageService.uploadMemberImage(memberId, file);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(
            @CurrentMemberId MemberId memberId,
            @PathVariable ImageId id) {
        imageService.delete(memberId, id);
        return ResponseEntity.ok().build();
    }
}


