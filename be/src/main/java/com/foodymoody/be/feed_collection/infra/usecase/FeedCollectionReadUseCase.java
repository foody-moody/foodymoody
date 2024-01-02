package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.application.StoreMoodReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
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
    private final StoreMoodReadService storeMoodReadService;
    private final FeedHeartService feedHeartService;

    public Slice<FeedCollectionSummary> fetchAll(Pageable pageable) {
        return feedCollectionReadService.fetchCollection(pageable);
    }

    @Transactional(readOnly = true)
    public FeedCollectionDetail fetchDetail(FeedCollectionId id) {
        var feedCollection = feedCollectionReadService.fetch(id);
        var author = memberQueryService.findById(feedCollection.getAuthorId());
        var authorProfileImage = imageService.findById(author.getProfileImageId());
        var authorTasteMood = tasteMoodReadService.findById(author.getTasteMoodId());
        AuthorSummaryResponse authorSummaryResponse = new AuthorSummaryResponse(
                author.getId().getValue(),
                author.getNickname(),
                authorProfileImage.getUrl(),
                authorTasteMood.getName()
        );
        var feedIds = feedCollection.getFeedIds();
        Slice<Feed> allDetailByIdIn = feedReadService.findAllByIdIn(
                feedIds, Pageable.ofSize(10).withPage(0));

        List<FeedSummaryResponse> feeds = allDetailByIdIn.stream()
                .map(feed -> new FeedSummaryResponse(
                        feed.getId().getValue(),
                        feed.getProfileImageUrl(),
                        feed.getReview(),
                        feed.getStoreMoods().stream()
                                .map(StoreMood::getName)
                                .collect(Collectors.toList()),
                        feed.getLikeCount(),
                        feed.getCommentCount(),
                        false,
                        feed.getCreatedAt(),
                        feed.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds);
    }

    @Transactional(readOnly = true)
    public FeedCollectionDetail fetchDetail(FeedCollectionId id, MemberId memberId) {
        var feedCollection = feedCollectionReadService.fetch(id);
        var author = memberQueryService.findById(feedCollection.getAuthorId());
        var authorProfileImage = imageService.findById(author.getProfileImageId());
        var authorTasteMood = tasteMoodReadService.findById(author.getTasteMoodId());
        AuthorSummaryResponse authorSummaryResponse = new AuthorSummaryResponse(
                author.getId().getValue(),
                author.getNickname(),
                authorProfileImage.getUrl(),
                authorTasteMood.getName()
        );
        var feedIds = feedCollection.getFeedIds();
        Slice<Feed> allDetailByIdIn = feedReadService.findAllByIdIn(
                feedIds, Pageable.ofSize(10).withPage(0));

        List<FeedSummaryResponse> feeds = allDetailByIdIn.stream()
                .map(feed -> new FeedSummaryResponse(
                        feed.getId().getValue(),
                        feed.getProfileImageUrl(),
                        feed.getReview(),
                        feed.getStoreMoods().stream()
                                .map(StoreMood::getName)
                                .collect(Collectors.toList()),
                        feed.getLikeCount(),
                        feed.getCommentCount(),
                        feedHeartService.existsHeart(memberId, feed.getId().getValue()),
                        feed.getCreatedAt(),
                        feed.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds);
    }
}
