package com.foodymoody.be.feed.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.application.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.application.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.application.dto.response.FeedImageResponse;
import com.foodymoody.be.feed.application.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.application.dto.response.FeedMenuResponse;
import com.foodymoody.be.feed.application.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.application.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.application.dto.response.FeedStoreMoodResponse;
import com.foodymoody.be.feed.application.dto.response.FeedTasteMoodResponse;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.time.LocalDateTime;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedMapperTest {

    @DisplayName("toFeed를 활용하여 피드 등록에서의 Feed Entity 객체를 만들고 받아올 수 있다.")
    @Test
    void toFeed() {
        // given
        FeedId id = makeFeedId();
        MemberId memberId = makeMemberId();
        String location = makeLocation();
        String review = makeReview();
        List<StoreMoodId> storeMoodIds = List.of(IdFactory.createStoreMoodId("1"), IdFactory.createStoreMoodId("2"));
        FeedServiceRegisterRequest feedServiceRegisterRequest = new FeedServiceRegisterRequest(memberId, location,
                review, storeMoodIds,
                List.of(
                        new ImageMenuPair(IdFactory.createImageId("1"),
                                new FeedRegisterRequestMenu("라면", 5)),
                        new ImageMenuPair(IdFactory.createImageId("2"),
                                new FeedRegisterRequestMenu("짬뽕", 4))));
        List<StoreMood> storeMoods = makeStoreMoods();
        List<Image> images = makeImages(memberId);
        List<Menu> menus = makeMenus();
        String profileImageUrl = makeProfileImageUrl();

        // when
        Feed feed = FeedMapper.toFeed(id, memberId, feedServiceRegisterRequest, storeMoods,
                images, menus, profileImageUrl);

        // then
        assertAll(
                () -> assertThat(feed.getId()).isEqualTo(id),
                () -> assertThat(feed.getMemberId()).isEqualTo(memberId),
                () -> assertThat(feed.getLocation()).isEqualTo(location),
                () -> assertThat(feed.getReview()).isEqualTo(review),
                () -> assertThat(feed.getStoreMoods()).isEqualTo(storeMoods),
                () -> assertThat(feed.getProfileImageUrl()).isEqualTo(profileImageUrl)
        );
    }

    @DisplayName("toFeedRegisterResponse()로 피드를 등록할 때의 FeedRegisterResponse를 만들 수 있다.")
    @Test
    void toFeedRegisterResponse() {
        // given
        Feed feed = generateFeed();

        // when
        FeedRegisterResponse feedRegisterResponse = FeedMapper.toFeedRegisterResponse(feed);

        // then
        assertThat(feedRegisterResponse.getId()).isEqualTo(feed.getId());
    }

    @DisplayName("toFeedReadResponse()로 피드 멤버 데이터를 만들 때의 FeedReadResponse를 만들 수 있다.")
    @Test
    void toFeedReadResponse() {
        // given
        MemberId memberId = makeMemberId();
        String profileImageUrl = makeProfileImageUrl();
        String nickname = "설리";
        Feed feed = generateFeed();
        FeedMemberResponse feedMemberResponse = new FeedMemberResponse(memberId, profileImageUrl, nickname,
                new FeedTasteMoodResponse(memberId, nickname));
        List<FeedImageMenuResponse> images = List.of(new FeedImageMenuResponse(feed.getId(),
                        new FeedImageResponse(IdFactory.createImageId("1"),
                                "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1"),
                        new FeedMenuResponse("라면", 5)),
                new FeedImageMenuResponse(feed.getId(), new FeedImageResponse(IdFactory.createImageId("2"),
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2"),
                        new FeedMenuResponse("짬뽕", 4)));
        List<FeedStoreMoodResponse> moodNames = List.of(
                new FeedStoreMoodResponse(IdFactory.createStoreMoodId("1"), "가족과 함께"),
                new FeedStoreMoodResponse(IdFactory.createStoreMoodId("2"), "혼밥"));

        // when
        FeedReadResponse feedReadResponse = FeedMapper.toFeedReadResponse(feedMemberResponse, feed, images, moodNames);

        // then
        assertAll(() -> {
            assertThat(feedReadResponse.getId()).isEqualTo(feed.getId());
            assertThat(feedReadResponse.getMember()).isEqualTo(feedMemberResponse);
            assertThat(feedReadResponse.getLocation()).isEqualTo(feed.getLocation());
            assertThat(feedReadResponse.getReview()).isEqualTo(feed.getReview());
            assertThat(feedReadResponse.getStoreMood()).isEqualTo(moodNames);
            assertThat(feedReadResponse.getImages()).isEqualTo(images);
        });
    }

    private Feed generateFeed() {
        FeedId feedId = makeFeedId();
        MemberId memberId = makeMemberId();
        String location = makeLocation();
        String review = makeReview();
        List<StoreMood> storeMoods = makeStoreMoods();
        List<Image> images = makeImages(memberId);
        List<Menu> menus = makeMenus();
        String profileImageUrl = makeProfileImageUrl();

        return new Feed(feedId, memberId, location, review, storeMoods, images, menus, profileImageUrl,
                LocalDateTime.MIN);
    }

    @NotNull
    private static String makeProfileImageUrl() {
        return "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1";
    }

    @NotNull
    private static List<Menu> makeMenus() {
        return List.of(new Menu(IdFactory.createMenuId(), "라면", 5),
                new Menu(IdFactory.createMenuId(), "짬뽕", 4));
    }

    @NotNull
    private static List<Image> makeImages(MemberId memberId) {
        return List.of(new Image(IdFactory.createImageId("1"),
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1", memberId),
                new Image(IdFactory.createImageId("2"),
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2", memberId));
    }

    @NotNull
    private static List<StoreMood> makeStoreMoods() {
        return List.of(new StoreMood(IdFactory.createStoreMoodId("1"), "가족과 함께"),
                new StoreMood(IdFactory.createStoreMoodId("2"), "혼밥"));
    }

    @NotNull
    private static String makeReview() {
        return "맛있어요";
    }

    @NotNull
    private static String makeLocation() {
        return "중동";
    }

    @NotNull
    private static MemberId makeMemberId() {
        return IdFactory.createMemberId();
    }

    @NotNull
    private static FeedId makeFeedId() {
        return IdFactory.createFeedId();
    }

}
