package com.foodymoody.be.image.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.MemberId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("이미지 카테고리 테스트")
class ImageCategoryTest {

    @DisplayName("피드 이미지 경로를 생성한다")
    @Test
    void whenGetFeedPrefix_thenSuccess() {
        assertThat(ImageCategory.FEED.getPrefix(null)).isEqualTo("feeds");
    }

    @DisplayName("회원 이미지 경로를 생성한다")
    @Test
    void whenGetMemberPrefix_thenSuccess() {
        assertThat(ImageCategory.MEMBER.getPrefix(new MemberId("memberId"))).isEqualTo("members/memberId");
    }
}
