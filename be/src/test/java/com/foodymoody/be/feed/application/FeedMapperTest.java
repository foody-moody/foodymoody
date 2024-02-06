package com.foodymoody.be.feed.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
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
import com.foodymoody.be.feed.application.dto.response.StoreResponse;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.entity.Menu;
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
        StoreId storeId = makeStoreId();
        String review = makeReview();
        List<StoreMoodId> storeMoodIds = List.of(IdFactory.createStoreMoodId("1"), IdFactory.createStoreMoodId("2"));
        FeedServiceRegisterRequest feedServiceRegisterRequest = new FeedServiceRegisterRequest(memberId,
                IdFactory.createStoreId("1"),
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
        Feed feed = Feed.builder()
                .id(id)
                .memberId(memberId)
                .storeId(storeId)
                .review(review)
                .storeMoods(storeMoods)
                .images(images)
                .menus(menus)
                .profileImageUrl(profileImageUrl)
                .build();

        // then
        assertAll(
                () -> assertThat(feed.getId()).isEqualTo(id),
                () -> assertThat(feed.getMemberId()).isEqualTo(memberId),
                () -> assertThat(feed.getStoreId()).isEqualTo(storeId),
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
        StoreResponse storeResponse = makeNewStoreResponse();
        FeedId id = feed.getId();
        LocalDateTime createdAt = feed.getCreatedAt();
        LocalDateTime updatedAt = feed.getUpdatedAt();
        String review = feed.getReview();
        int likeCount = feed.getLikeCount();

        // when
        var feedReadResponse = FeedReadResponse.builder()
                .id(id)
                .member(feedMemberResponse)
                .storeResponse(storeResponse)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .review(review)
                .storeMood(moodNames)
                .images(images)
                .likeCount(likeCount)
                .isLiked(false)
                .commentCount(0L)
                .build();

        // then
        assertAll(() -> {
            assertThat(feedReadResponse.getId()).isEqualTo(id);
            assertThat(feedReadResponse.getMember()).isEqualTo(feedMemberResponse);
            assertThat(feedReadResponse.getStore()).isEqualTo(storeResponse);
            assertThat(feedReadResponse.getReview()).isEqualTo(review);
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
        FeedMemberResponse feedMemberResponse = makeFeedMemberResponse(memberId, profileImageUrl, nickname);
        List<FeedStoreMoodResponse> feedStoreMoodResponses = makeFeedStoreMoodResponse();
        List<FeedImageMenuResponse> feedImageMenuResponses = makeFeedImageMenuResponse(feed);
        StoreResponse storeResponse = makeNewStoreResponse();

        // when
        FeedId id = feed.getId();
        LocalDateTime createdAt = feed.getCreatedAt();
        LocalDateTime updatedAt = feed.getUpdatedAt();
        String review = feed.getReview();
        int likeCount = feed.getLikeCount();
        var feedReadAllResponse = FeedReadAllResponse.builder()
                .id(id)
                .member(feedMemberResponse)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .storeResponse(storeResponse)
                .review(review)
                .storeMood(feedStoreMoodResponses)
                .images(feedImageMenuResponses)
                .likeCount(likeCount)
                .isLiked(false)
                .commentCount(0L)
                .build();

        // then
        assertAll(() -> {
            assertThat(feedReadAllResponse.getId()).isEqualTo(id);
            assertThat(feedReadAllResponse.getMember()).isEqualTo(feedMemberResponse);
            assertThat(feedReadAllResponse.getStore()).isEqualTo(storeResponse);
            assertThat(feedReadAllResponse.getReview()).isEqualTo(review);
            assertThat(feedReadAllResponse.getStoreMood()).isEqualTo(feedStoreMoodResponses);
            assertThat(feedReadAllResponse.getImages()).isEqualTo(feedImageMenuResponses);
        });
    }

    @NotNull
    private static StoreResponse makeNewStoreResponse() {
        return FeedMapper.makeStoreResponse(IdFactory.createStoreId("1"), "영업중 식당");
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
        StoreId storeId = makeStoreId();
        String review = makeReview();
        List<StoreMood> storeMoods = makeStoreMoods();
        List<Image> images = makeImages(memberId);
        List<Menu> menus = makeMenus();
        String profileImageUrl = makeProfileImageUrl();

        return new Feed(feedId, memberId, storeId, review, storeMoods, images, menus, profileImageUrl,
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
                FeedStoreMoodResponse.from(IdFactory.createStoreMoodId("1"), "가족과 함께"),
                FeedStoreMoodResponse.from(IdFactory.createStoreMoodId("2"), "혼밥"));
    }

    @NotNull
    private List<FeedImageMenuResponse> makeFeedImageMenuResponse(Feed feed) {
        return List.of(FeedImageMenuResponse.from(feed.getId(),
                        FeedImageResponse.from(IdFactory.createImageId("1"),
                                "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png1"),
                        FeedMenuResponse.from("라면", 5)),
                FeedImageMenuResponse.from(feed.getId(),
                        FeedImageResponse.from(IdFactory.createImageId("2"),
                                "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png2"),
                        FeedMenuResponse.from("짬뽕", 4))
        );
    }

    @NotNull
    private List<StoreMood> makeStoreMoods() {
        return List.of(new StoreMood(IdFactory.createStoreMoodId("1"), "가족과 함께"),
                new StoreMood(IdFactory.createStoreMoodId("2"), "혼밥"));
    }

    @NotNull
    private FeedMemberResponse makeFeedMemberResponse(
            MemberId memberId,
            String profileImageUrl,
            String nickname
    ) {
        return FeedMemberResponse.builder()
                .id(memberId)
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .tasteMood(FeedTasteMoodResponse.from(memberId, nickname))
                .build();
    }

    @NotNull
    private String makeReview() {
        return "맛있어요";
    }

    @NotNull
    private StoreId makeStoreId() {
        return IdFactory.createStoreId("1");
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
