package com.foodymoody.be.image;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

import com.foodymoody.be.image.controller.ImageUploadResponse;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.repository.ImageRepository;
import com.foodymoody.be.image.repository.ImageStorage;
import com.foodymoody.be.image.service.ImageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

    @Nested
    @DisplayName("이미지 저장 테스트")
    class Save {

        @DisplayName("이미지를 저장하면, 이미지 id와 이미지가 저장된 s3 url을 반환한다.")
        @Test
        void whenSaveImageSuccess_thenReturnIdAndS3Url() {
//        given
            given(imageStorage.upload(any(), any())).willReturn("https://s3Url/key");
            when(imageRepository.save(any(Image.class))).thenAnswer(invocation -> invocation.getArgument(0));
            MockMultipartFile file = new MockMultipartFile("file", "test.jpg", "image/jpg", (byte[]) null);

//        when
            ImageUploadResponse response = imageService.save(file);

//        then
            Assertions.assertAll(
                    () -> assertThat(response.getId()).isNotNull(),
                    () -> assertThat(response.getUrl()).isEqualTo("https://s3Url/key")
            );
        }

    }

}
