package com.foodymoody.be.feed_collection.infra.usecase;

import static com.foodymoody.be.feed_collection.infra.usecase.FeedCollectionMapper.toAuthorSummaryResponse;
import static com.foodymoody.be.feed_collection.infra.usecase.FeedCollectionMapper.toCommentResponse;
import static com.foodymoody.be.feed_collection.infra.usecase.FeedCollectionMapper.toFeedCollectionResponse;
import static com.foodymoody.be.feed_collection.infra.usecase.FeedCollectionMapper.toFeedSummaryResponse;
import static com.foodymoody.be.feed_collection.infra.usecase.FeedCollectionMapper.toStoreMoodResponse;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.application.dto.response.StoreMoodResponse;
import com.foodymoody.be.feed.domain.entity.Feed;
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
                .map(this::getFeedCollectionResponse);
    }

    @Transactional(readOnly = true)
    public Slice<FeedCollectionResponse> fetchAll(MemberId memberId, Pageable pageable) {
        return feedCollectionReadService.fetchCollection(memberId, pageable)
                .map(this::getFeedCollectionResponse);
    }

    @Transactional(readOnly = true)
    public FeedCollectionDetail fetchDetail(FeedCollectionId id) {
        var feedCollection = feedCollectionReadService.fetch(id);
        var authorSummaryResponse = getAuthorSummaryResponse(feedCollection);
        var feeds = getFeedSummaryResponse(feedCollection.getFeedIds());
        var comments = getComments(feedCollection.getCommentIds());
        var moods = getMoodResponses(id);
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds, comments, moods);
    }

    @Transactional(readOnly = true)
    public FeedCollectionDetail fetchDetail(FeedCollectionId id, MemberId memberId) {
        var feedCollection = feedCollectionReadService.fetch(id);
        var authorSummaryResponse = getAuthorSummaryResponse(feedCollection);
        var feeds = getFeedSummaryResponse(memberId, feedCollection);
        var comments = getComments(memberId, feedCollection.getCommentIds());
        var moods = getMoodResponses(id);
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds, comments, moods);
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
        return toFeedSummaryResponse(feeds);
    }

    private List<FeedSummaryResponse> getFeedSummaryResponse(MemberId memberId, Slice<Feed> feeds) {
        return feeds.stream()
                .map(feed -> {
                    boolean isLiked = feedHeartService.existsHeart(memberId, feed.getId().getValue());
                    List<StoreMoodResponse> moods = toStoreMoodResponse(feed.getStoreMoods());
                    return toFeedSummaryResponse(feed, moods, isLiked);
                })
                .collect(Collectors.toList());
    }

    private AuthorSummaryResponse getAuthorSummaryResponse(FeedCollection feedCollection) {
        var author = memberQueryService.findById(feedCollection.getAuthorId());
        var authorProfileImage = imageService.findById(author.getProfileImageId());
        var authorTasteMood = tasteMoodReadService.findById(author.getTasteMoodId());
        return toAuthorSummaryResponse(author, authorProfileImage, authorTasteMood);
    }
}
