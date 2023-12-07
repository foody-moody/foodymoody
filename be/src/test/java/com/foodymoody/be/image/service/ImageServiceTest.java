package com.foodymoody.be.image.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.when;

import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.controller.ImageUploadResponse;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.repository.ImageRepository;
import com.foodymoody.be.image.repository.ImageStorage;
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
    ImageStorage imageStorage;
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
    @DisplayName("이미지 저장 테스트")
    class Save {

        @DisplayName("이미지를 저장하면, 이미지 id와 이미지가 저장된 s3 url을 반환한다.")
        @Test
        void whenSaveImageSuccess_thenReturnIdAndS3Url() {
//        given
            given(imageStorage.upload(any(), any())).willReturn("https://s3Url/key");
            when(imageRepository.save(any(Image.class))).thenAnswer(invocation -> invocation.getArgument(0));
            MockMultipartFile file = createMockMultipartFileByPath("images/potato.jpg", "images/potato.jpg");

//        when
            ImageUploadResponse response = imageService.save(ImageCategory.MEMBER, "memberId", file);

//        then
            assertThat(response.getId()).isNotNull();
        }

    }

    @Nested
    @DisplayName("이미지 삭제 테스트")
    class Delete {

        @DisplayName("이미지를 삭제한다")
        @Test
        void whenDeleteImage_thenSuccess() {
//        given
            ImageId testId = IdFactory.createImageId("testId");
            given(imageRepository.findById(any(ImageId.class))).willReturn(
                    Optional.of(new Image(testId, "https://s3Url/key", IdFactory.createMemberId("testMemberId")))
            );

//        when, then
            Assertions.assertDoesNotThrow(() -> imageService.delete("testMemberId", "testId"));
        }

        @DisplayName("이미지 업로더의 id와 매개변수로 받은 회원 id가 다르면, 예외가 발생한다")
        @Test
        void whenRequestMemberIdIsNotImageUploaderId_thenFail() {
//        given
            ImageId testId = IdFactory.createImageId("testId");
            given(imageRepository.findById(any(ImageId.class))).willReturn(
                    Optional.of(new Image(testId, "https://s3Url.com/key", new MemberId("testMemberId"))));

//        when, then
            Assertions.assertThrows(UnauthorizedException.class,
                    () -> imageService.delete("differentMemberId", "testId"));
        }

    }

}
