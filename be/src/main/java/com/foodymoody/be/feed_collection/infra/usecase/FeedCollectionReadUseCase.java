package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed_collection.application.FeedCollectionMoodReadService;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.AuthorSummaryResponse;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionDetail;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.feed_collection.domain.FeedSummaryResponse;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionCommentResponse;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionMoodResponse;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionResponse;
import com.foodymoody.be.feed_collection_comment.application.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentSummary;
import com.foodymoody.be.feed_heart.application.FeedHeartService;
import com.foodymoody.be.image.application.ImageService;
import com.foodymoody.be.member.application.MemberQueryService;
import com.foodymoody.be.member.application.TasteMoodReadService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedCollectionReadUseCase {

    private final FeedCollectionReadService feedCollectionReadService;
    private final FeedReadService feedReadService;
    private final MemberQueryService memberQueryService;
    private final ImageService imageService;
    private final TasteMoodReadService tasteMoodReadService;
    private final FeedHeartService feedHeartService;
    private final FeedCollectionCommentReadService commentReadService;
    private final FeedCollectionMoodReadService feedCollectionMoodReadService;

    @Transactional(readOnly = true)
    public Slice<FeedCollectionResponse> fetchAll(Pageable pageable) {
        return feedCollectionReadService.fetchCollection(pageable)
                .map(this::toFeedCollectionResponse);
    }

    @Transactional(readOnly = true)
    public Slice<FeedCollectionResponse> fetchAll(MemberId memberId, Pageable pageable) {
        return feedCollectionReadService.fetchCollection(memberId, pageable)
                .map(this::toFeedCollectionResponse);
    }

    @Transactional(readOnly = true)
    public FeedCollectionDetail fetchDetail(FeedCollectionId id) {
        var feedCollection = feedCollectionReadService.fetch(id);
        var authorSummaryResponse = getAuthorSummaryResponse(feedCollection);
        var feeds = getFeeds(feedCollection.getFeedIds());
        var comments = getComments(feedCollection.getCommentIds());
        var moods = getMoodResponses(id);
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds, comments, moods);
    }

    @Transactional(readOnly = true)
    public FeedCollectionDetail fetchDetail(FeedCollectionId id, MemberId memberId) {
        var feedCollection = feedCollectionReadService.fetch(id);
        var authorSummaryResponse = getAuthorSummaryResponse(feedCollection);
        var feeds = getFeeds(memberId, feedCollection);
        var comments = getComments(memberId, feedCollection.getCommentIds());
        var moods = getMoodResponses(id);
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds, comments, moods);
    }

    private FeedCollectionResponse toFeedCollectionResponse(FeedCollectionSummary summary) {
        List<FeedCollectionMoodResponse> moodResponses = getMoodResponses(
                summary.getId());
        return new FeedCollectionResponse(
                summary.getId(),
                summary.getThumbnailUrl(),
                summary.getAuthor(),
                summary.getTitle(),
                summary.getDescription(),
                summary.getLikeCount(),
                summary.isLiked(),
                summary.getFollowerCount(),
                summary.getCommentCount(),
                summary.getFeedCount(),
                moodResponses,
                summary.getCreatedAt(),
                summary.getUpdatedAt()
        );
    }

    private List<FeedCollectionMoodResponse> getMoodResponses(FeedCollectionId id) {
        List<FeedCollectionMood> moods = feedCollectionMoodReadService.findByFeedCollectionId(id);
        return moods.stream()
                .map(mood -> new FeedCollectionMoodResponse(mood.getId(), mood.getName()))
                .collect(Collectors.toList());
    }

    private Slice<FeedCollectionCommentResponse> getComments(
            MemberId memberId,
            List<FeedCollectionCommentId> commentIds
    ) {
        Slice<FeedCollectionCommentSummary> comments = commentReadService.findSummaryAllByIdIn(
                memberId, commentIds, Pageable.ofSize(10).withPage(0));
        return toCommentResponse(comments);
    }

    private List<FeedSummaryResponse> getFeeds(MemberId memberId, FeedCollection feedCollection) {
        var feedIds = feedCollection.getFeedIds();
        Slice<Feed> feeds = feedReadService.findAllByIdIn(
                feedIds,
                Pageable.ofSize(10).withPage(0)
        );
        return feeds.stream()
                .map(feed -> {
                    boolean isLiked = feedHeartService.existsHeart(memberId, feed.getId().getValue());
                    List<String> moods = getStoreMoodsNames(feed.getStoreMoods());
                    return getFeedSummaryResponse(feed, moods, isLiked);
                })
                .collect(Collectors.toList());
    }

    private AuthorSummaryResponse getAuthorSummaryResponse(FeedCollection feedCollection) {
        var author = memberQueryService.findById(feedCollection.getAuthorId());
        var authorProfileImage = imageService.findById(author.getProfileImageId());
        var authorTasteMood = tasteMoodReadService.findById(author.getTasteMoodId());
        return new AuthorSummaryResponse(
                author.getId(),
                author.getNickname(),
                authorProfileImage.getUrl(),
                authorTasteMood.getName()
        );
    }

    private Slice<FeedCollectionCommentResponse> getComments(List<FeedCollectionCommentId> commentIds) {
        Slice<FeedCollectionCommentSummary> comments = commentReadService.findSummaryAllByIdIn(
                commentIds,
                Pageable.ofSize(10).withPage(0)
        );
        return toCommentResponse(comments);
    }

    private List<FeedSummaryResponse> getFeeds(List<FeedId> feedIds) {
        Slice<Feed> feeds = feedReadService.findAllByIdIn(
                feedIds, Pageable.ofSize(10).withPage(0));
        return feeds.stream()
                .map(feed -> {
                    List<String> moods = getStoreMoodsNames(feed.getStoreMoods());
                    return getFeedSummaryResponse(feed, moods, false);
                })
                .collect(Collectors.toList());
    }

    private FeedSummaryResponse getFeedSummaryResponse(
            Feed feed, List<String> moods, boolean isLiked
    ) {
        return new FeedSummaryResponse(
                feed.getId().getValue(),
                feed.getProfileImageUrl(),
                feed.getReview(),
                moods,
                feed.getLikeCount(),
                feed.getCommentCount(),
                isLiked,
                feed.getCreatedAt(),
                feed.getUpdatedAt()
        );
    }

    private static Slice<FeedCollectionCommentResponse> toCommentResponse(
            Slice<FeedCollectionCommentSummary> comments
    ) {
        return comments.map(comment -> new FeedCollectionCommentResponse(
                comment.getId(),
                comment.getFeedId(),
                comment.getContent(),
                comment.isDeleted(),
                comment.isHasReply(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.isLiked(),
                comment.getLikeCount(),
                comment.getMemberId(),
                comment.getNickname(),
                comment.getProfileImageUrl(),
                comment.getMood()
        ));
    }

    private static List<String> getStoreMoodsNames(List<StoreMood> storeMoods) {
        return storeMoods.stream()
                .map(StoreMood::getName)
                .collect(Collectors.toList());
    }
}
