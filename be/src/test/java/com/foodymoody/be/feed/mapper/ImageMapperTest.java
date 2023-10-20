package com.foodymoody.be.feed.mapper;

import static org.assertj.core.api.Assertions.*;

import com.foodymoody.be.feed.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.util.ImageMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageMapperTest {

    @DisplayName("이미지와 메뉴 목록을 이미지 List 엔티티로 매핑한다.")
    @Test
    void toImage() {
        // given
        ImageMenuPair 마라탕_Request = new ImageMenuPair("https://www.naver.com", new FeedRegisterRequestMenu("마라탕", 4));
        ImageMenuPair 떡볶이_Request = new ImageMenuPair("https://www.google.com", new FeedRegisterRequestMenu("떡볶이", 5));

        // when
        List<Image> actualImages = ImageMapper.toImage(List.of(마라탕_Request, 떡볶이_Request));

        // then
        List<Image> expectedImages = List.of(new Image("https://www.naver.com"), new Image("https://www.google.com"));
        assertThat(actualImages)
                .usingRecursiveComparison()
                .isEqualTo(expectedImages);
    }

}
