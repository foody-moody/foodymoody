package com.foodymoody.be.feed.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.application.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.ImageMenuPair;
import com.foodymoody.be.feed.application.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.application.dto.response.FeedImageResponse;
import com.foodymoody.be.feed.application.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.application.dto.response.FeedMenuResponse;
import com.foodymoody.be.feed.application.dto.response.FeedReadAllResponse;
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

@DisplayName("FeedMapper 테스트")
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
        Feed feed = makeFeed();

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
        String nickname = makeNickname();
        Feed feed = makeFeed();
        FeedMemberResponse feedMemberResponse = makeFeedMemberResponse(memberId, profileImageUrl, nickname);
        List<FeedImageMenuResponse> images = makeFeedImageMenuResponse(
                feed);
        List<FeedStoreMoodResponse> moodNames = makeFeedStoreMoodResponse();

        // when
        FeedReadResponse feedReadResponse = FeedMapper.toFeedReadResponse(feedMemberResponse, feed, images, moodNames, false);

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

    @DisplayName("makeFeedReadAllResponse()로 피드 전체 조회할 때의 FeedReadAllResponse를 만들 수 있다.")
    @Test
    void makeFeedReadAllResponse() {
        // given
        Feed feed = makeFeed();
        MemberId memberId = makeMemberId();
        String profileImageUrl = makeProfileImageUrl();
        String nickname = makeNickname();
        FeedMemberResponse makeFeedMemberResponse = makeFeedMemberResponse(memberId, profileImageUrl, nickname);
        List<FeedStoreMoodResponse> makeFeedStoreMoodResponses = makeFeedStoreMoodResponse();
        List<FeedImageMenuResponse> makeFeedImageMenuResponses = makeFeedImageMenuResponse(feed);

        // when
        FeedReadAllResponse feedReadAllResponse = FeedMapper.makeFeedReadAllResponse(feed, makeFeedMemberResponse,
                makeFeedStoreMoodResponses,
                makeFeedImageMenuResponses,
                false);

        // then
        assertAll(() -> {
            assertThat(feedReadAllResponse.getId()).isEqualTo(feed.getId());
            assertThat(feedReadAllResponse.getMember()).isEqualTo(makeFeedMemberResponse);
            assertThat(feedReadAllResponse.getLocation()).isEqualTo(feed.getLocation());
            assertThat(feedReadAllResponse.getReview()).isEqualTo(feed.getReview());
            assertThat(feedReadAllResponse.getStoreMood()).isEqualTo(makeFeedStoreMoodResponses);
            assertThat(feedReadAllResponse.getImages()).isEqualTo(makeFeedImageMenuResponses);
        });
    }

    @DisplayName("makeFeedStoreMoodResponses()로 StoreMood 형식 그대로의 Response를 만들 수 있다.")
    @Test
    void makeFeedStoreMoodResponses() {
        // given
        List<StoreMood> storeMoods = makeStoreMoods();

        // when
        List<FeedStoreMoodResponse> feedStoreMoodResponses = FeedMapper.makeFeedStoreMoodResponses(storeMoods);

        // then
        assertThat(feedStoreMoodResponses).hasSameSizeAs(storeMoods);
    }

    @DisplayName("toFeedStoreMoodNames()로 StoreMood의 Name만을 뽑아낼 수 있다.")
    @Test
    void toFeedStoreMoodNames() {
        // given
        List<StoreMood> storeMoods = makeStoreMoods();
        List<String> expected = List.of("가족과 함께", "혼밥");

        // when
        List<String> feedStoreMoodNames = FeedMapper.toFeedStoreMoodNames(storeMoods);

        // then
        assertThat(feedStoreMoodNames).isEqualTo(expected);
    }

    @DisplayName("findFirstImageId()로 Feed의 첫 번째 Image Id를 찾아낼 수 있다.")
    @Test
    void findFirstImageId() {
        // given
        Feed feed = makeFeed();
        ImageId expected = IdFactory.createImageId("1");

        // when
        ImageId firstImageId = FeedMapper.findFirstImageId(feed);

        // then
        assertThat(firstImageId).isEqualTo(expected);
    }

    private Feed makeFeed() {
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
    private String makeProfileImageUrl() {
        return "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1";
    }

    @NotNull
    private String makeNickname() {
        return "설리";
    }

    @NotNull
    private List<Menu> makeMenus() {
        return List.of(new Menu(IdFactory.createMenuId(), "라면", 5),
                new Menu(IdFactory.createMenuId(), "짬뽕", 4));
    }

    @NotNull
    private List<Image> makeImages(MemberId memberId) {
        return List.of(new Image(IdFactory.createImageId("1"),
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1", memberId),
                new Image(IdFactory.createImageId("2"),
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2", memberId));
    }

    @NotNull
    private List<FeedStoreMoodResponse> makeFeedStoreMoodResponse() {
        return List.of(
                new FeedStoreMoodResponse(IdFactory.createStoreMoodId("1"), "가족과 함께"),
                new FeedStoreMoodResponse(IdFactory.createStoreMoodId("2"), "혼밥"));
    }

    @NotNull
    private List<FeedImageMenuResponse> makeFeedImageMenuResponse(Feed feed) {
        return List.of(new FeedImageMenuResponse(feed.getId(),
                        new FeedImageResponse(IdFactory.createImageId("1"),
                                "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1"),
                        new FeedMenuResponse("라면", 5)),
                new FeedImageMenuResponse(feed.getId(), new FeedImageResponse(IdFactory.createImageId("2"),
                        "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2"),
                        new FeedMenuResponse("짬뽕", 4)));
    }

    @NotNull
    private List<StoreMood> makeStoreMoods() {
        return List.of(new StoreMood(IdFactory.createStoreMoodId("1"), "가족과 함께"),
                new StoreMood(IdFactory.createStoreMoodId("2"), "혼밥"));
    }

    @NotNull
    private FeedMemberResponse makeFeedMemberResponse(MemberId memberId, String profileImageUrl,
                                                      String nickname) {
        return new FeedMemberResponse(memberId, profileImageUrl, nickname,
                new FeedTasteMoodResponse(memberId, nickname));
    }

    @NotNull
    private String makeReview() {
        return "맛있어요";
    }

    @NotNull
    private String makeLocation() {
        return "중동";
    }

    @NotNull
    private MemberId makeMemberId() {
        return IdFactory.createMemberId();
    }

    @NotNull
    private FeedId makeFeedId() {
        return IdFactory.createFeedId();
    }

}
