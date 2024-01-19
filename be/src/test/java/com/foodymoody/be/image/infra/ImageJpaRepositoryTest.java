package com.foodymoody.be.image.infra;


import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.config.AppConfig;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.infra.persistence.jpa.ImageJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DisplayName("ImageRepository 테스트")
@DataJpaTest
@Import(AppConfig.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ImageJpaRepositoryTest {

    @Autowired
    private ImageJpaRepository jpaRepository;

    @Test
    @DisplayName("이미지를 soft delete 하면 이미지가 조회되지 않는다")
    void when_set_deleted_true_then_success() {
        // given
        Image image1 = new Image(new ImageId("testImageId1"), "testImageUrl1", new MemberId("testImageUploaderId1"));
        Image image2 = new Image(new ImageId("testImageId2"), "testImageUrl2", new MemberId("testImageUploaderId2"));
        Image image3 = new Image(new ImageId("testImageId3"), "testImageUrl3", new MemberId("testImageUploaderId3"));
        jpaRepository.save(image1);
        jpaRepository.save(image2);
        jpaRepository.save(image3);
        List<ImageId> imageIds = Stream.of(image1, image2, image3).
                map(Image::getId)
                .collect(Collectors.toUnmodifiableList());

        // when
        jpaRepository.setDeletedTrueInBatch(List.of(image1, image2));

        // then
        List<Image> actual = jpaRepository.findAllByIdInAndDeletedFalse(imageIds);
        assertThat(actual).containsExactly(image3);
    }

    @Test
    @DisplayName("이미지를 저장하면 이미지가 조회된다")
    void when_save_and_find_then_success() {
        // given
        Image image1 = new Image(new ImageId("testImageId1"), "testImageUrl1", new MemberId("testImageUploaderId1"));
        Image image2 = new Image(new ImageId("testImageId2"), "testImageUrl2", new MemberId("testImageUploaderId2"));
        Image image3 = new Image(new ImageId("testImageId3"), "testImageUrl3", new MemberId("testImageUploaderId3"));
        jpaRepository.save(image1);
        jpaRepository.save(image2);
        jpaRepository.save(image3);
        List<ImageId> imageIds = Stream.of(image1, image2, image3).
                map(Image::getId)
                .collect(Collectors.toUnmodifiableList());

        // when
        List<Image> actual = jpaRepository.findAllByIdInAndDeletedFalse(imageIds);

        // then
        assertThat(actual).containsExactly(image1, image2, image3);
    }

}
