package com.foodymoody.be.feed.util;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.domain.StoreMood;
import com.foodymoody.be.feed.domain.StoreMoodId;
import com.foodymoody.be.feed.dto.request.FeedRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceDeleteRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceRegisterRequest;
import com.foodymoody.be.feed.dto.request.FeedServiceUpdateRequest;
import com.foodymoody.be.feed.dto.request.FeedUpdateRequest;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.dto.response.FeedMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedRegisterResponse;
import com.foodymoody.be.feed.dto.response.FeedStoreMoodResponse;
import com.foodymoody.be.feed.dto.response.FeedTasteMoodResponse;
import com.foodymoody.be.feed.service.dto.ImageIdNamePair;
import com.foodymoody.be.feed.service.dto.MenuNameRatingPair;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.member.repository.MemberFeedData;
import com.foodymoody.be.menu.domain.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FeedMapper {

    private FeedMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Feed toFeed(FeedId id, MemberId memberId, FeedServiceRegisterRequest request, List<String> moodIds,
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
                .build();
    }

    public static FeedServiceRegisterRequest toServiceRegisterRequest(FeedRegisterRequest request, String memberId) {
        return FeedServiceRegisterRequest.builder()
                .memberId(memberId)
                .location(request.getLocation())
                .review(request.getReview())
                .storeMood(request.getStoreMood())
                .images(request.getImages())
                .build();
    }

    public static FeedServiceUpdateRequest toServiceUpdateRequest(FeedUpdateRequest request, String memberId) {
        return FeedServiceUpdateRequest.builder()
                .memberId(memberId)
                .location(request.getLocation())
                .review(request.getReview())
                .storeMood(request.getStoreMood())
                .images(request.getImages())
                .build();
    }

    public static List<FeedImageMenuResponse> toFeedImageMenuResponses(List<ImageIdNamePair> imageIdUrlList,
                                                                       List<MenuNameRatingPair> menuNameRatingList) {
        List<FeedImageMenuResponse> feedImageMenuResponses = new ArrayList<>();
        for (int i = 0; i < imageIdUrlList.size(); i++) {
            feedImageMenuResponses.add(
                    new FeedImageMenuResponse(
                            imageIdUrlList.get(i).getId(), imageIdUrlList.get(i).getUrl(),
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

    public static List<StoreMoodId> makeStoreMoodIds(List<String> storeMoodIds) {
        return storeMoodIds.stream()
                .map(StoreMoodId::new)
                .collect(Collectors.toUnmodifiableList());
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

    public static List<FeedStoreMoodResponse> makeFeedStoreMoodResponses(List<String> storeMoodIds,
                                                                         List<StoreMood> storeMoods) {
        return IntStream.range(0, storeMoodIds.size())
                .mapToObj(i -> new FeedStoreMoodResponse(storeMoodIds.get(i), storeMoods.get(i).getName()))
                .collect(Collectors.toUnmodifiableList());
    }

}
