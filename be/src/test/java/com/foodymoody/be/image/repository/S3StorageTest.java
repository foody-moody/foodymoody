package com.foodymoody.be.image.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.exception.InvalidImageUrlException;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.domain.ImageCategory;
import com.foodymoody.be.image.domain.ImageResource;
import com.foodymoody.be.image.infra.persistence.S3Storage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
@ContextConfiguration(classes = S3Storage.class)
@DisplayName("S3 저장소 테스트")
class S3StorageTest {

    private final String s3EndPoint;
    private final String rootPrefix;
    @Autowired
    private S3Storage s3Storage;

    S3StorageTest(
            @Value("${aws.s3.endpoint}") String s3EndPoint,
            @Value("${aws.s3.root-prefix.image}") String rootPrefix) {
        this.s3EndPoint = s3EndPoint;
        this.rootPrefix = rootPrefix;
    }

    @Nested
    @DisplayName("키 생성 테스트")
    class GenerateKey {

        @DisplayName("s3 키를 생성하면, 형식에 맞는 키가 생성된다")
        @Test
        void whenGenerateKey_thenSuccess() {
//            given, when
            String memberImageUrl = s3Storage.generateKey(ImageCategory.MEMBER, new MemberId("1"), "uuid", "fileName.jpg");
            String feedImageUrl = s3Storage.generateKey(ImageCategory.FEED, null, "uuid", "fileName.jpg");

//            then
            Assertions.assertAll(
                    () -> assertThat(memberImageUrl).isEqualTo("images/members/1/uuid/fileName.jpg"),
                    () -> assertThat(feedImageUrl).isEqualTo("images/feeds/uuid/fileName.jpg")
            );
        }

    }

    @Nested
    @DisplayName("url에서 key 추출 테스트")
    class GetKey {

        @DisplayName("유효한 url이 매개변수로 주어질 시, key가 추출된다")
        @Test
        void whenGetKeyFromUrl_thenSuccess() {
//            given
            String url = String.join("/", s3EndPoint, rootPrefix, "test");

//            when, then
            assertThat(s3Storage.getKey(url)).isEqualTo(String.join("/", rootPrefix, "test"));
        }

        @DisplayName("유효하지 않은 url이 매개변수로 주어질 시, 예외가 발생한다")
        @Test
        void whenGetKeyFromWrongUrl_thenError() {
//            given
            final String URL_WITH_INVALID_S3_ENDPOINT = "https://foodymoody.site/testtest";
            final String URL_WITH_VALID_S3_ENDPOINT_AND_INVALID_ROOT_PREFIX = String.join("/", s3EndPoint, "test");

//            when, then
            Assertions.assertAll(
                    () -> Assertions.assertThrows(InvalidImageUrlException.class,
                            () -> s3Storage.getKey(URL_WITH_INVALID_S3_ENDPOINT)),
                    () -> Assertions.assertThrows(InvalidImageUrlException.class,
                            () -> s3Storage.getKey(URL_WITH_VALID_S3_ENDPOINT_AND_INVALID_ROOT_PREFIX))
            );
        }

    }

    @Nested
    @DisplayName("업로드 테스트")
    class Upload {

        @DisplayName("jpg 파일을 업로드하면, 파일이 저장된 s3경로를 반환한다")
        @Test
        void whenUploadJpgFile_thenUploadToS3() {
//            given
            MockMultipartFile file = createMockMultipartFileByPath("images/potato.jpg");
            ImageResource imageResource = new ImageResource(file);

//            when
            String url = s3Storage.upload("upload-test", imageResource);

//            then
            String expectedUrl = String.join("/", s3EndPoint, "upload-test");
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
            MockMultipartFile file = createMockMultipartFileByPath("images/potato.jpg");
            ImageResource imageResource = new ImageResource(file);
            String url = s3Storage.upload("delete-test", imageResource);

//            when, then
            Assertions.assertDoesNotThrow(
                    () -> s3Storage.delete("delete-test")
            );
        }

    }

    @Nested
    @DisplayName("배치 삭제 테스트")
    class DeleteInBatch {

        @DisplayName("jpg 파일을 s3에 업로드 후 삭제하면, 예외가 발생하지 않는다")
        @Test
        void whenUploadJpgFilesAndDeleteInBatch_thenSuccess() {
//            given
            MockMultipartFile file = createMockMultipartFileByPath("images/potato.jpg");
            ImageResource imageResource = new ImageResource(file);
            s3Storage.upload("delete-test1", imageResource);
            s3Storage.upload("delete-test2", imageResource);

//            when
            boolean hasNotError = s3Storage.deleteInBatch(List.of("delete-test1", "delete-test2"));

//            then
            assertThat(hasNotError).isTrue();
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
            throw new RuntimeException(e);
        }
    }

}
