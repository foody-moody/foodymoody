package com.foodymoody.be.feed_collection.application.usecase;

import static com.foodymoody.be.feed_collection.application.usecase.FeedCollectionMapper.toAuthorSummaryResponse;
import static com.foodymoody.be.feed_collection.application.usecase.FeedCollectionMapper.toCommentResponse;
import static com.foodymoody.be.feed_collection.application.usecase.FeedCollectionMapper.toFeedCollectionResponse;
import static com.foodymoody.be.feed_collection.application.usecase.FeedCollectionMapper.toFeedSummaryResponse;
import static com.foodymoody.be.feed_collection.application.usecase.FeedCollectionMapper.toStoreMoodResponse;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.dto.response.StoreMoodResponse;
import com.foodymoody.be.feed.application.service.FeedCommentCountReadService;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionMoodReadService;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionCommentResponse;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionMoodResponse;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionResponse;
import com.foodymoody.be.feed_collection.domain.AuthorSummaryResponse;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionDetail;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.feed_collection.domain.FeedSummaryResponse;
import com.foodymoody.be.feed_collection_comment.application.service.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentSummary;
import com.foodymoody.be.feed_collection_like.application.service.FeedCollectionLikeReadService;
import com.foodymoody.be.feed_like.application.service.FeedLikeService;
import com.foodymoody.be.image.application.service.ImageService;
import com.foodymoody.be.member.application.service.FollowReadService;
import com.foodymoody.be.member.application.service.MemberReadService;
import com.foodymoody.be.member.application.service.TasteMoodReadService;
import com.foodymoody.be.store.application.service.StoreReadService;
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
    private final MemberReadService memberReadService;
    private final ImageService imageService;
    private final TasteMoodReadService tasteMoodReadService;
    private final FeedLikeService feedLikeService;
    private final FeedCollectionCommentReadService commentReadService;
    private final FeedCollectionMoodReadService feedCollectionMoodReadService;
    private final FeedCommentCountReadService feedCommentCountReadService;
    private final StoreReadService storeReadService;
    private final FeedCollectionLikeReadService feedCollectionLikeReadService;
    private final FollowReadService followReadService;

    @Transactional(readOnly = true)
    public Slice<FeedCollectionResponse> fetchAll(Pageable pageable) {
        return feedCollectionReadService.fetchCollection(pageable)
                .map(this::getFeedCollectionResponse);
    }

    @Transactional(readOnly = true)
    public Slice<FeedCollectionResponse> fetchAll(MemberId memberId, Pageable pageable) {
        return feedCollectionReadService.fetchCollection(memberId, pageable)
                .map(this::getFeedCollectionResponse);
    }

    @Transactional(readOnly = true)
    public FeedCollectionDetail fetchDetail(FeedCollectionId id) {
        var feedCollection = feedCollectionReadService.fetchById(id);
        var authorSummaryResponse = getAuthorSummaryResponse(feedCollection);
        var feeds = getFeedSummaryResponse(feedCollection.getFeedIds());
        var comments = getComments(feedCollection.getCommentIds());
        var moods = getMoodResponses(id);
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds, comments, moods, false, false);
    }

    @Transactional(readOnly = true)
    public FeedCollectionDetail fetchDetail(FeedCollectionId id, MemberId memberId) {
        var feedCollection = feedCollectionReadService.fetchById(id, memberId);
        var isLiked = feedCollectionLikeReadService.isLiked(id, memberId);
        var authorSummaryResponse = getAuthorSummaryResponse(feedCollection);
        var feeds = getFeedSummaryResponse(memberId, feedCollection);
        var comments = getComments(memberId, feedCollection.getCommentIds());
        var moods = getMoodResponses(id);
        var isFollowed = followReadService.isFollowing(memberId, feedCollection.getAuthorId());
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds, comments, moods, isLiked,
                                        isFollowed
        );
    }

    @Transactional(readOnly = true)
    public Slice<FeedCollectionCommentResponse> fetchComments(FeedCollectionId id, Pageable pageable) {
        var feedCollection = feedCollectionReadService.fetchById(id);
        var commentIds = feedCollection.getCommentIds();
        var comments = commentReadService.findSummaryAllByIdIn(
                commentIds,
                pageable
        );
        return toCommentResponse(comments);
    }

    @Transactional(readOnly = true)
    public Slice<FeedCollectionCommentResponse> fetchComments(
            FeedCollectionId id, MemberId memberId, Pageable pageable
    ) {
        var feedCollection = feedCollectionReadService.fetchById(id);
        var commentIds = feedCollection.getCommentIds();
        var comments = commentReadService.findSummaryAllByIdIn(
                memberId,
                commentIds,
                pageable
        );
        return toCommentResponse(comments);
    }

    private FeedCollectionResponse getFeedCollectionResponse(FeedCollectionSummary summary) {
        List<FeedCollectionMoodResponse> moodResponses = getMoodResponses(summary.getId());
        return toFeedCollectionResponse(summary, moodResponses);
    }

    private List<FeedCollectionMoodResponse> getMoodResponses(FeedCollectionId id) {
        List<FeedCollectionMood> moods = feedCollectionMoodReadService.findByFeedCollectionId(id);
        return moods.stream()
                .map(mood -> new FeedCollectionMoodResponse(mood.getId(), mood.getName()))
                .collect(Collectors.toList());
    }

    private Slice<FeedCollectionCommentResponse> getComments(List<FeedCollectionCommentId> commentIds) {
        Slice<FeedCollectionCommentSummary> comments = commentReadService.findSummaryAllByIdIn(
                commentIds,
                Pageable.ofSize(10).withPage(0)
        );
        return toCommentResponse(comments);
    }

    private Slice<FeedCollectionCommentResponse> getComments(
            MemberId memberId,
            List<FeedCollectionCommentId> commentIds
    ) {
        Slice<FeedCollectionCommentSummary> comments = commentReadService.findSummaryAllByIdIn(
                memberId, commentIds, Pageable.ofSize(10).withPage(0));
        return toCommentResponse(comments);
    }

    private List<FeedSummaryResponse> getFeedSummaryResponse(MemberId memberId, FeedCollection feedCollection) {
        var feedIds = feedCollection.getFeedIds();
        Slice<Feed> feeds = feedReadService.findAllByIdIn(
                feedIds,
                Pageable.ofSize(10).withPage(0)
        );
        return getFeedSummaryResponse(memberId, feeds);
    }

    private List<FeedSummaryResponse> getFeedSummaryResponse(List<FeedId> feedIds) {
        Slice<Feed> feeds = feedReadService.findAllByIdIn(
                feedIds,
                Pageable.ofSize(10).withPage(0)
        );
        return toFeedSummaryResponseForCommentCount(feeds);
    }

    private List<FeedSummaryResponse> toFeedSummaryResponseForCommentCount(Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> {
                    List<StoreMoodResponse> moods = toStoreMoodResponse(feed.getStoreMoods());
                    int commentCount = feedCommentCountReadService.fetchCountByFeedId(feed.getId()).intValue();
                    var storeId = feed.getStoreId();
                    var store = storeReadService.findById(storeId);
                    return toFeedSummaryResponse(feed, moods, false, commentCount, storeId, store.getName());
                })
                .collect(Collectors.toList());
    }

    private List<FeedSummaryResponse> getFeedSummaryResponse(MemberId memberId, Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> {
                    boolean isLiked = feedLikeService.existsHeart(memberId, feed.getId().getValue());
                    List<StoreMoodResponse> moods = toStoreMoodResponse(feed.getStoreMoods());
                    int commentCount = feedCommentCountReadService.fetchCountByFeedId(feed.getId()).intValue();
                    var storeId = feed.getStoreId();
                    var store = storeReadService.findById(storeId);
                    return toFeedSummaryResponse(feed, moods, isLiked, commentCount, storeId, store.getName());
                })
                .collect(Collectors.toList());
    }

    private AuthorSummaryResponse getAuthorSummaryResponse(FeedCollection feedCollection) {
        var author = memberReadService.findById(feedCollection.getAuthorId());
        var authorProfileImage = imageService.findById(author.getProfileImageId());
        var authorTasteMood = tasteMoodReadService.findById(author.getTasteMoodId());
        return toAuthorSummaryResponse(author, authorProfileImage, authorTasteMood);
    }
}
