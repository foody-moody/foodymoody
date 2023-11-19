package com.foodymoody.be.image;

import static org.assertj.core.api.Assertions.*;

import com.foodymoody.be.image.repository.ImageStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
@ContextConfiguration(classes = ImageStorage.class)
@DisplayName("이미지 저장소 테스트")
class ImageStorageTest {

    @Autowired
    private ImageStorage imageStorage;
    private final String region;
    private final String bucketName;

    ImageStorageTest(
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.bucket}") String bucketName) {
        this.region = region;
        this.bucketName = bucketName;
    }

    @Nested
    @DisplayName("업로드 테스트")
    class Upload {

        @DisplayName("jpg 파일을 업로드하면, 파일이 저장된 s3경로를 반환한다")
        @Test
        void whenUploadJpgFile_thenUploadToS3() {
//            given
            MockMultipartFile file = createMockMultipartFileByPath("potato.jpg");

//            when
            String url = imageStorage.upload(file, "upload-test");

//            then
            String expectedUrl = createS3Url("upload-test");
            assertThat(url).isEqualTo(expectedUrl);
        }

    }

    @Nested
    @DisplayName("삭제 테스트")
    class Delete {

        @DisplayName("jpg 파일을 s3에 업로드 후 삭제하면, 예외가 발생하지 않는다")
        @Test
        void whenUploadJpgFileAndDelete_thenSuccess() {
//            given
            MockMultipartFile file = createMockMultipartFileByPath("potato.jpg");
            String url = imageStorage.upload(file, "delete-test");

//            when, then
            Assertions.assertDoesNotThrow(
                    () ->imageStorage.delete("delete-test")
            );
        }

    }

    private MockMultipartFile createMockMultipartFileByPath(String path) {
        Resource resource = new ClassPathResource(path);
        try {
            Path absolutePath = resource.getFile().toPath();
            String MIMEType = Files.probeContentType(absolutePath);
            byte[] bytes = Files.readAllBytes(absolutePath);
            return new MockMultipartFile("file", resource.getFilename(), MIMEType, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String createS3Url(String key) {
        return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
    }

}
