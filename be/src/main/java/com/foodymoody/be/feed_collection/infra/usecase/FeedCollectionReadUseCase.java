package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.application.StoreMoodService;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed.domain.entity.StoreMoodId;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.image.service.ImageService;
import com.foodymoody.be.member.service.MemberService;
import com.foodymoody.be.member.service.TasteMoodService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReadUseCase {

    private final FeedCollectionReadService feedCollectionReadService;
    private final FeedReadService feedReadService;
    private final MemberService memberService;
    private final ImageService imageService;
    private final TasteMoodService tasteMoodService;
    private final StoreMoodService storeMoodService;

    public Slice<FeedCollectionSummary> fetchAll(Pageable pageable) {
        return feedCollectionReadService.fetchCollection(pageable);
    }

    public FeedCollectionDetail fetchDetail(String id) {
        var feedCollectionId = IdFactory.createFeedCollectionId(id);
        var feedCollection = feedCollectionReadService.fetch(feedCollectionId);
        var author = memberService.findById(feedCollection.getAuthorId());
        var authorProfileImage = imageService.findById(author.getProfileImageId());
        var authorTasteMood = tasteMoodService.findById(author.getTasteMoodId());
        AuthorSummaryResponse authorSummaryResponse = new AuthorSummaryResponse(
                author.getId().getValue(),
                author.getNickname(),
                authorProfileImage.getUrl(),
                authorTasteMood.getName()
        );
        var feedIds = feedCollection.getFeedIds();
        List<FeedSummaryResponse> feeds = feedIds.stream()
                .map(feedReadService::findFeed)
                .map(feed -> {
                    var feedTasteMood = storeMoodService.findAllById(
                                    feed.getStoreMoods()
                                            .getStoreMoodIds()
                                            .stream()
                                            .map(StoreMoodId::new)
                                            .collect(Collectors.toList())
                            ).stream()
                            .map(StoreMood::getName)
                            .collect(Collectors.toList());
                    return new FeedSummaryResponse(
                            feed.getId().getValue(),
                            feed.getProfileImageUrl(),
                            feed.getReview(),
                            feedTasteMood,
                            feed.getLikeCount(),
                            false,
                            feed.getCommentCount(),
                            feed.getCreatedAt(),
                            feed.getUpdatedAt()
                    );
                })
                .collect(Collectors.toList());
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds);
    }
}
