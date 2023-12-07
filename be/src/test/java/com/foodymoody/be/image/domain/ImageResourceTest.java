package com.foodymoody.be.image.domain;


import com.foodymoody.be.common.exception.InvalidImageFileException;
import com.foodymoody.be.common.exception.UnsupportedImageFormatException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;

@DisplayName("이미지 리소스 테스트")
class ImageResourceTest {

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

    static class EmptyOrNullFileProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(new MockMultipartFile("file", "empty.jpg", "multipart/form-data", new byte[]{})),
                    Arguments.of(new MockMultipartFile("file", "null.png", "multipart/form-data", (byte[]) null)),
                    Arguments.of(createMockMultipartFileByPath("images/potato.jpg", null)),
                    Arguments.of(createMockMultipartFileByPath("images/potato.jpg", "    "))
            );
        }
    }

    @DisplayName("생성자 테스트")
    @Nested
    class Constructor {

        @DisplayName("이미지가 유효하고 지원하는 형식이면, 객체가 생성된다")
        @ParameterizedTest
        @CsvSource({"images/potato.jpg, potato.jpg", "images/sample.png, sample.png"})
        void whenSupportedFormat_thenConstructImageFile(String path, String fileName) {
//        given
            MockMultipartFile mock = createMockMultipartFileByPath(path, fileName);

//        when, then
            Assertions.assertDoesNotThrow(() -> new ImageResource(mock));
        }

        @DisplayName("지원하지 않는 이미지 형식이면, 예외가 발생한다")
        @ParameterizedTest
        @CsvSource({"images/sample.avif, sample.avif", "images/test.txt, test.txt"})
        void whenUnsupportedFormat_thenDoNotConstructImageFile(String path, String fileName) {
//        given
            MockMultipartFile mock = createMockMultipartFileByPath(path, fileName);

//        when, then
            Assertions.assertThrows(UnsupportedImageFormatException.class, () -> new ImageResource(mock));
        }


        @DisplayName("파일 혹은 파일이름이 null이거나 비어있으면, 예외가 발생한다")
        @ParameterizedTest
        @ArgumentsSource(EmptyOrNullFileProvider.class)
        void whenFileOrContentTypeOrFileNameIsNullOrEmpty_thenDoNotConstructImageFile(MockMultipartFile mock) {
//        when, then
            Assertions.assertThrows(InvalidImageFileException.class, () -> new ImageResource(mock));
        }
    }

}
