package com.foodymoody.be.image.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
class ImageCategoryTest {

    @DisplayName("피드 이미지 경로를 생성한다")
    @Test
    void whenGetFeedPrefix_thenSuccess() {
        assertThat(ImageCategory.FEED.getPrefix("feedId")).isEqualTo("feeds/feedId");
    }

    @DisplayName("회원 이미지 경로를 생성한다")
    @Test
    void whenGetMemberPrefix_thenSuccess() {
        assertThat(ImageCategory.MEMBER.getPrefix("memberId")).isEqualTo("members/memberId");
    }
}