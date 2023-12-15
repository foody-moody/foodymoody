package com.foodymoody.be.feed.application;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreMoodId;
import com.foodymoody.be.feed.application.dto.request.CollectionServiceReadAllFeedRequest;
import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceDeleteRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.application.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.application.dto.request.FeedUpdateRequest;
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
import com.foodymoody.be.feed.infra.usecase.dto.ImageIdNamePair;
import com.foodymoody.be.feed.infra.usecase.dto.MenuNameRatingPair;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.member.repository.MemberFeedData;
import com.foodymoody.be.menu.domain.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.domain.Pageable;

public class FeedMapper {

    private FeedMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Feed toFeed(FeedId id, MemberId memberId, FeedServiceRegisterRequest request, List<StoreMoodId> moodIds,
                              List<Image> images, List<Menu> menus, String profileImageUrl) {
        return new Feed(id, memberId, request.getLocation(), request.getReview(), moodIds, images, menus,
                profileImageUrl);
    }

    public static FeedRegisterResponse toFeedRegisterResponse(Feed savedFeed) {
        return new FeedRegisterResponse(savedFeed.getId().getValue());
    }

    public static FeedReadResponse toFeedReadResponse(FeedMemberResponse feedMemberResponse, Feed feed,
                                                      List<FeedImageMenuResponse> images,
                                                      List<FeedStoreMoodResponse> moodNames) {
        return FeedReadResponse.builder()
                .id(feed.getId().getValue())
                .member(feedMemberResponse)
                .location(feed.getLocation())
                .review(feed.getReview())
                .storeMood(moodNames)
                .images(images)
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .isLiked(feed.isLiked())
                .likeCount(feed.getLikeCount())
                .commentCount(feed.getCommentCount())
                .build();
    }

    public static FeedServiceRegisterRequest toServiceRegisterRequest(FeedRegisterRequest request, String memberId) {
        return FeedServiceRegisterRequest.builder()
                .memberId(memberId)
                .location(request.getLocation())
                .review(request.getReview())
                .storeMoodIds(request.getStoreMoodIds())
                .images(request.getImages())
                .build();
    }

    public static FeedServiceUpdateRequest toServiceUpdateRequest(FeedUpdateRequest request, String memberId) {
        return FeedServiceUpdateRequest.builder()
                .memberId(memberId)
                .location(request.getLocation())
                .review(request.getReview())
                .storeMoodIds(request.getStoreMoodIds())
                .images(request.getImages())
                .build();
    }

    public static List<FeedImageMenuResponse> toFeedImageMenuResponses(List<ImageIdNamePair> imageIdUrlList,
                                                                       List<MenuNameRatingPair> menuNameRatingList) {
        List<FeedImageMenuResponse> feedImageMenuResponses = new ArrayList<>();
        for (int i = 0; i < imageIdUrlList.size(); i++) {
            feedImageMenuResponses.add(
                    new FeedImageMenuResponse(
                            IdGenerator.generate(),
                            new FeedImageResponse(imageIdUrlList.get(i).getId(),
                                    imageIdUrlList.get(i).getUrl()),
                            new FeedMenuResponse(menuNameRatingList.get(i).getName(),
                                    menuNameRatingList.get(i).getRating())
                    )
            );
        }

        return feedImageMenuResponses;
    }

    public static FeedServiceDeleteRequest toServiceDeleteRequest(String id, String memberId) {
        return new FeedServiceDeleteRequest(id, memberId);
    }

    public static FeedMemberResponse toFeedMemberResponse(MemberFeedData member) {
        return FeedMemberResponse.builder()
                .id(member.getId())
                .imageUrl(member.getProfileImageUrl())
                .nickname(member.getNickname())
                .tasteMood(new FeedTasteMoodResponse(member.getId(), member.getMoodName()))
                .build();
    }

    public static FeedReadAllResponse makeFeedReadAllResponse(Feed feed, FeedMemberResponse makeFeedMemberResponse,
                                                              List<FeedStoreMoodResponse> makeFeedStoreMoodResponses,
                                                              List<FeedImageMenuResponse> makeFeedImageMenuResponses) {
        return FeedReadAllResponse.builder()
                .id(feed.getId().getValue())
                .member(makeFeedMemberResponse)
                .location(feed.getLocation())
                .review(feed.getReview())
                .storeMood(makeFeedStoreMoodResponses)
                .images(makeFeedImageMenuResponses)
                .createdAt(feed.getCreatedAt())
                .updatedAt(feed.getUpdatedAt())
                .commentCount(feed.getCommentCount())
                .isLiked(feed.isLiked())
                .likeCount(feed.getLikeCount())
                .build();
    }

    public static List<FeedStoreMoodResponse> makeFeedStoreMoodResponses(List<StoreMoodId> storeMoodIds,
                                                                         List<StoreMood> storeMoods) {
        return IntStream.range(0, storeMoodIds.size())
                .mapToObj(i -> new FeedStoreMoodResponse(storeMoodIds.get(i), storeMoods.get(i).getName()))
                .collect(Collectors.toUnmodifiableList());
    }

    public static CollectionServiceReadAllFeedRequest toCollectionServiceReadAllFeedRequest(String collectionId,
                                                                                            Pageable pageable) {
        return new CollectionServiceReadAllFeedRequest(collectionId, pageable);
    }

}
