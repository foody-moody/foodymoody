package com.foodymoody.be.feed.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedMapper;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.application.StoreMoodReadService;
import com.foodymoody.be.feed.application.dto.request.CollectionReadFeedListServiceRequest;
import com.foodymoody.be.feed.application.dto.response.CollectionReadAllFeedResponse;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.image.application.ImageService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CollectionUseCase {

    private final FeedCollectionReadService feedCollectionReadService;
    private final FeedReadService feedReadService;
    private final ImageService imageService;
    private final StoreMoodReadService storeMoodReadService;

    public Slice<CollectionReadAllFeedResponse> readFeedCollectionDetail(CollectionReadFeedListServiceRequest request) {
        FeedCollectionId feedCollectionId = request.getFeedCollectionId();
        Pageable pageable = request.getPageable();
        MemberId memberId = request.getMemberId();

        final String sortBy = "createdAt";
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy).descending());

        FeedCollection feedCollection = feedCollectionReadService.fetch(feedCollectionId);
        List<FeedId> feedIds = feedCollection.getFeedIds();
        Slice<Feed> feeds = feedReadService.findAllByIdIn(feedIds, pageable);

        List<CollectionReadAllFeedResponse> responses;
        if (memberId == null) {
            responses = makeCollectionReadAllFeedResponsesWhenMemberIdIsNull(feeds);
        } else {
            responses = makeCollectionReadAllFeedResponsesWhenMemberIdIsNotNull(feeds);
        }

        return new SliceImpl<>(responses, pageable, feeds.hasNext());
    }

    private List<CollectionReadAllFeedResponse> makeCollectionReadAllFeedResponsesWhenMemberIdIsNull(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> CollectionReadAllFeedResponse.builder()
                        .feedAllCount(feeds.getSize())
                        .feedThumbnailUrl(imageService.findById(FeedMapper.findFirstImageId(feed)).getUrl())
                        .storeName(null) // TODO: 가게 API 구현 완료되면 가게 name 조회해오기
                        .feedId(feed.getId())
                        .createdAt(feed.getCreatedAt())
                        .updatedAt(feed.getUpdatedAt())
                        .description(feed.getReview())
                        .moodNames(FeedMapper.toFeedStoreMoodNames(feed.getStoreMoods()))
                        .isLiked(false)
                        .likeCount(feed.getLikeCount())
                        .feedCommentCount(feed.getCommentCount())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    private List<CollectionReadAllFeedResponse> makeCollectionReadAllFeedResponsesWhenMemberIdIsNotNull(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> CollectionReadAllFeedResponse.builder()
                        .feedAllCount(feeds.getSize())
                        .feedThumbnailUrl(imageService.findById(FeedMapper.findFirstImageId(feed)).getUrl())
                        .storeName(null) // TODO: 가게 API 구현 완료되면 가게 name 조회해오기
                        .feedId(feed.getId())
                        .createdAt(feed.getCreatedAt())
                        .updatedAt(feed.getUpdatedAt())
                        .description(feed.getReview())
                        .moodNames(FeedMapper.toFeedStoreMoodNames(feed.getStoreMoods()))
                        .isLiked(feedReadService.fetchIsLikedByMemberId(feed.getId(), feed.getMemberId()))
                        .likeCount(feed.getLikeCount())
                        .feedCommentCount(feed.getCommentCount())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

}
