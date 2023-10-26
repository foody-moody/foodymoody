package com.foodymoody.be.feed.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.feed.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.util.ImageMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageMapperTest {

    @DisplayName("생성된 id와 url을 성공적으로 매핑할수 있다.")
    @Test
    void toImage() {
        // given
        ImageMenuPair 마라탕_Request = new ImageMenuPair("https://www.naver.com", new FeedRegisterRequestMenu("마라탕", 4));
        String generatedId = "1";

        // when
        Image actualImage = ImageMapper.getImage(generatedId, 마라탕_Request);

        // then
        Image expectedImage = new Image(generatedId, "https://www.naver.com");
        assertThat(actualImage)
                .usingRecursiveComparison()
                .isEqualTo(expectedImage);
    }

}
