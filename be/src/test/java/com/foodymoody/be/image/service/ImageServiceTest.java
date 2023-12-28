package com.foodymoody.be.image.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.when;

import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.application.ImageService;
import com.foodymoody.be.image.domain.ImageRepository;
import com.foodymoody.be.image.infra.persistence.S3Storage;
import com.foodymoody.be.image.presentation.dto.response.ImageUploadResponse;
import com.foodymoody.be.image.domain.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

@DisplayName("이미지 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

    @InjectMocks
    ImageService imageService;
    @Mock
    S3Storage imageStorage;
    @Mock
    ImageRepository imageRepository;

    private static MockMultipartFile createMockMultipartFileByPath(String path, String fileName) {
        Resource resource = new ClassPathResource(path);
        try {
            Path absolutePath = resource.getFile().toPath();
            byte[] bytes = Files.readAllBytes(absolutePath);
            return new MockMultipartFile("file", fileName, "multipart/form-data", bytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Nested
    @DisplayName("피드 이미지 업로드 테스트")
    class UploadFeedImage {

        @DisplayName("피드 이미지를 업로드하면, 이미지 id와 이미지가 저장된 s3 url을 반환한다.")
        @Test
        void whenUploadFeedImageSuccess_thenReturnIdAndS3Url() {
//        given
            when(imageStorage.generateKey(any(), any(), any(), any())).thenCallRealMethod();
            when(imageStorage.upload(any(String.class), any())).thenAnswer(invocation -> "https://s3Url/" + invocation.getArgument(0));
            when(imageRepository.save(any(Image.class))).thenAnswer(invocation -> invocation.getArgument(0));
            MockMultipartFile file = createMockMultipartFileByPath("images/potato.jpg", "images/potato.jpg");

//        when
            ImageUploadResponse response = imageService.uploadFeedImage(new MemberId( "memberId"), file);

//        then
            Assertions.assertAll(
                    () -> assertThat(response.getId()).isNotNull(),
                    () -> assertThat(response.getUrl()).matches("https://s3Url/null/feeds/.+/potato.jpg")
            );
        }

    }

    @Nested
    @DisplayName("회원 이미지 업로드 테스트")
    class UploadMemberImage {

        @DisplayName("회원 이미지를 업로드하면, 이미지 id와 이미지가 저장된 s3 url을 반환한다.")
        @Test
        void whenUploadMemberImageSuccess_thenReturnIdAndS3Url() {
//        given
            when(imageStorage.generateKey(any(), any(), any(), any())).thenCallRealMethod();
            when(imageStorage.upload(any(String.class), any())).thenAnswer(invocation -> "https://s3Url/" + invocation.getArgument(0));
            when(imageRepository.save(any(Image.class))).thenAnswer(invocation -> invocation.getArgument(0));
            MockMultipartFile file = createMockMultipartFileByPath("images/potato.jpg", "images/potato.jpg");

//        when
            ImageUploadResponse response = imageService.uploadMemberImage(new MemberId( "memberId"), file);

//        then
            Assertions.assertAll(
                    () -> assertThat(response.getId()).isNotNull(),
                    () -> assertThat(response.getUrl()).matches("https://s3Url/null/members/memberId/.+/potato.jpg")
            );
        }

    }

    @Nested
    @DisplayName("이미지 soft delete 테스트")
    class SoftDelete {

        @DisplayName("이미지를 soft delete 한다")
        @Test
        void whenSoftDeleteImage_thenSuccess() {
//        given
            ImageId testId = IdFactory.createImageId("testId");
            given(imageRepository.findByIdAndDeletedFalse(any(ImageId.class))).willReturn(
                    Optional.of(new Image(testId, "https://s3Url/key", IdFactory.createMemberId("testMemberId")))
            );

//        when, then
            Assertions.assertDoesNotThrow(() -> imageService.softDelete(new MemberId("testMemberId"), new ImageId("testId")));
        }

        @DisplayName("이미지 업로더의 id와 매개변수로 받은 회원 id가 다르면, 예외가 발생한다")
        @Test
        void whenRequestMemberIdIsNotImageUploaderId_thenFail() {
//        given
            ImageId testId = IdFactory.createImageId("testId");
            given(imageRepository.findByIdAndDeletedFalse(any(ImageId.class))).willReturn(
                    Optional.of(new Image(testId, "https://s3Url.com/key", new MemberId("testMemberId"))));

//        when, then
            Assertions.assertThrows(UnauthorizedException.class,
                    () -> imageService.softDelete(new MemberId("differentMemberId"), new ImageId("testId")));
        }

    }

}
