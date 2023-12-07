package com.foodymoody.be.image.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.exception.InvalidImageUrlException;
import com.foodymoody.be.image.domain.ImageCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
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
            String actual = s3Storage.generateKey(ImageCategory.MEMBER, "1", "uuid", "fileName.jpg");

//            then
            assertThat(actual).isEqualTo("images/members/1/uuid/fileName.jpg");
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

}
