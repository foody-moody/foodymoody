package com.foodymoody.be.feed.application;

import com.foodymoody.be.common.exception.ImageNotFoundException;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeCountId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.feed.application.dto.request.CollectionReadFeedDetailsServiceRequest;
import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceDeleteRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.application.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.application.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.application.dto.response.FeedImageResponse;
import com.foodymoody.be.feed.application.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.application.dto.response.FeedMenuResponse;
import com.foodymoody.be.feed.application.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.application.dto.response.FeedStoreMoodResponse;
import com.foodymoody.be.feed.application.dto.response.FeedTasteMoodResponse;
import com.foodymoody.be.feed.application.dto.response.StoreResponse;
import com.foodymoody.be.feed.application.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.application.usecase.dto.MenuNameRatingPair;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;

public class FeedMapper {

    private FeedMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static FeedRegisterResponse toFeedRegisterResponse(Feed savedFeed) {
        return FeedRegisterResponse.of(savedFeed.getId());
    }

    public static FeedServiceRegisterRequest toServiceRegisterRequest(FeedRegisterRequest request, MemberId memberId) {
        return FeedServiceRegisterRequest.builder()
                .memberId(memberId)
                .storeId(request.getStoreId())
                .review(request.getReview())
                .storeMoodIds(request.getStoreMoodIds())
                .images(request.getImages())
                .build();
    }

    public static FeedServiceUpdateRequest toServiceUpdateRequest(
            FeedId id,
            FeedUpdateRequest request,
            MemberId memberId
    ) {
        return FeedServiceUpdateRequest.builder()
                .id(id)
                .memberId(memberId)
                .storeId(request.getStoreId())
                .review(request.getReview())
                .storeMoodIds(request.getStoreMoodIds())
                .images(request.getImages())
                .build();
    }

    public static List<FeedImageMenuResponse> toFeedImageMenuResponses(
            List<ImageIdNamePair> imageIdUrlList,
            List<MenuNameRatingPair> menuNameRatingList
    ) {
        List<FeedImageMenuResponse> feedImageMenuResponses = new ArrayList<>();
        for (int i = 0; i < imageIdUrlList.size(); i++) {
            FeedId feedId = IdFactory.createFeedId();
            ImageId id = imageIdUrlList.get(i).getId();
            String url = imageIdUrlList.get(i).getUrl();
            String name = menuNameRatingList.get(i).getName();
            int rating = menuNameRatingList.get(i).getRating();
            feedImageMenuResponses.add(
                    FeedImageMenuResponse.from(
                            feedId,
                            FeedImageResponse.from(id, url),
                            FeedMenuResponse.from(name, rating)
                    )
            );
        }

        return feedImageMenuResponses;
    }

    public static FeedServiceDeleteRequest toServiceDeleteRequest(FeedId id, MemberId memberId) {
        return new FeedServiceDeleteRequest(id, memberId);
    }

    public static FeedMemberResponse toFeedMemberResponse(FeedAuthorSummary member) {
        return FeedMemberResponse.builder()
                .id(member.getId())
                .profileImageUrl(member.getProfileImageUrl())
                .nickname(member.getNickname())
                .tasteMood(FeedTasteMoodResponse.from(member.getId(), member.getMoodName()))
                .build();
    }

    public static List<FeedStoreMoodResponse> makeFeedStoreMoodResponses(List<StoreMood> storeMoods) {
        return storeMoods.stream()
                .map(storeMood -> FeedStoreMoodResponse.from(storeMood.getId(), storeMood.getName()))
                .collect(Collectors.toList());
    }

    public static CollectionReadFeedDetailsServiceRequest toCollectionReadFeedDetailsServiceRequest(
            FeedCollectionId collectionId,
            Pageable pageable,
            MemberId memberId
    ) {
        return new CollectionReadFeedDetailsServiceRequest(collectionId, pageable, memberId);
    }

    public static List<String> toFeedStoreMoodNames(List<StoreMood> storeMoods) {
        return storeMoods.stream()
                .map(StoreMood::getName)
                .collect(Collectors.toList());
    }

    public static ImageId findFirstImageId(Feed feed) {
        return feed.getImageMenus().stream()
                .filter(imageMenu -> imageMenu.getDisplayOrder() == 0)
                .findFirst()
                .orElseThrow(ImageNotFoundException::new)
                .getImageId();
    }

    public static List<ImageId> makeImageIds(List<ImageMenu> imageMenus) {
        return imageMenus.stream()
                .map(ImageMenu::getImageId)
                .collect(Collectors.toList());
    }

    public static StoreResponse makeStoreResponse(StoreId id, String name) {
        return StoreResponse.from(id, name);
    }

    public static FeedLikeCount makeFeedLikeCount(FeedLikeCountId id, FeedId feedId, int count) {
        return new FeedLikeCount(id, feedId, count);
    }

}
