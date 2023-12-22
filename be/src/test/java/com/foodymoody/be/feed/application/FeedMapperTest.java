package com.foodymoody.be.feed.application;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedMapperTest {

    @DisplayName("피드 등록에서의 Feed Entity 객체를 만들고 받아올 수 있다.")
    @Test
    void toFeed() {
        // given
        FeedId feedId = IdFactory.createFeedId();
        MemberId memberId = IdFactory.createMemberId();
        String location = "중동";
        String review = "맛있어요";
        List<StoreMood> storeMoods = List.of(new StoreMood(IdFactory.createStoreMoodId(), "가족과 함께"),
                new StoreMood(IdFactory.createStoreMoodId(), "혼밥"));
        List<Image> images = List.of(new Image(IdFactory.createImageId("1"),
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1", memberId),
                new Image(IdFactory.createImageId("2"),
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2", memberId));
        List<Menu> menus = List.of(new Menu(IdFactory.createMenuId(), "라면", 5),
                new Menu(IdFactory.createMenuId(), "짬뽕", 4));
        String profileImageUrl = "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1";

        // when
        Feed feed = new Feed(feedId, memberId, location, review, storeMoods, images, menus, profileImageUrl,
                LocalDateTime.MIN);

        // then
        assertAll(
                () -> assertThat(feed.getId()).isEqualTo(feedId),
                () -> assertThat(feed.getMemberId()).isEqualTo(memberId),
                () -> assertThat(feed.getLocation()).isEqualTo(location),
                () -> assertThat(feed.getReview()).isEqualTo(review),
                () -> assertThat(feed.getStoreMoods()).isEqualTo(storeMoods),
                () -> assertThat(feed.getProfileImageUrl()).isEqualTo(profileImageUrl),
                () -> assertThat(feed.getCreatedAt()).isEqualTo(LocalDateTime.MIN)
        );
    }

}
