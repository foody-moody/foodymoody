package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.application.StoreMoodReadService;
import com.foodymoody.be.feed.domain.entity.StoreMood;
import com.foodymoody.be.feed_collection.application.FeedCollectionReadService;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.image.application.ImageService;
import com.foodymoody.be.member.application.MemberQueryService;
import com.foodymoody.be.member.application.TasteMoodReadService;
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
    private final MemberQueryService memberQueryService;
    private final ImageService imageService;
    private final TasteMoodReadService tasteMoodReadService;
    private final StoreMoodReadService storeMoodReadService;

    public Slice<FeedCollectionSummary> fetchAll(Pageable pageable) {
        return feedCollectionReadService.fetchCollection(pageable);
    }

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
        List<FeedSummaryResponse> feeds = feedIds.stream()
                .map(feedReadService::findFeed)
                .map(feed -> {
                    var feedTasteMood = feed.getStoreMoods()
                            .stream()
                            .map(StoreMood::getName)
                            .collect(Collectors.toList());
                    return new FeedSummaryResponse(
                            feed.getId().getValue(),
                            feed.getProfileImageUrl(),
                            feed.getReview(),
                            feedTasteMood,
                            feed.getLikeCount(),
                            feed.getCommentCount(),
                            feed.getCreatedAt(),
                            feed.getUpdatedAt()
                    );
                })
                .collect(Collectors.toList());
        return new FeedCollectionDetail(feedCollection, authorSummaryResponse, feeds);
    }
}
