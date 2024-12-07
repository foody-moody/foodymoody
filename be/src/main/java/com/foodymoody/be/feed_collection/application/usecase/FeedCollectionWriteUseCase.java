package com.foodymoody.be.feed_collection.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionMoodWriteService;
import com.foodymoody.be.feed_collection.application.service.FeedCollectionWriteService;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionCreateRequest;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionEditRequest;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionFeedsUpdateRequest;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionWriteUseCase {

    private final FeedCollectionWriteService service;
    private final FeedReadService feedReadService;
    private final FeedCollectionMoodWriteService moodService;

    public FeedCollectionId create(FeedCollectionCreateRequest request, MemberId memberId) {
        List<FeedCollectionMood> moods = new ArrayList<>();
        if (request.getMoodIds() != null && !request.getMoodIds().isEmpty()) {
            moods = moodService.findAllById(request.getMoodIds());
        }
        return service.create(
                request.getTitle(),
                request.getDescription() == null ? "" : request.getDescription(),
                request.isPrivate(),
                memberId,
                moods
        );
    }

    public void edit(FeedCollectionId id, FeedCollectionEditRequest request, MemberId memberId) {
        var moods = moodService.findAllById(request.getMoodIds());
        service.edit(
                id,
                request.getTitle(),
                request.getContent(),
                moods,
                memberId
        );
    }

    @Transactional
    public void update(FeedCollectionId id, FeedCollectionFeedsUpdateRequest request, MemberId memberId) {
        var feedIds = request.getFeedIds();
        var thumbnailUrl = request.getThumbnailUrl();
        feedReadService.validateIds(feedIds);
        if (thumbnailUrl == null && feedIds.isEmpty()) {
            return;
        }
        if (thumbnailUrl == null || thumbnailUrl.isBlank()) {
            thumbnailUrl = feedReadService.findFeed(feedIds.get(0)).getProfileImageUrl();
        }
        service.update(id, feedIds, memberId, thumbnailUrl);
    }

    public void delete(FeedCollectionId id, MemberId memberId) {
        service.delete(id, memberId);
    }

    public void addFeed(FeedCollectionId id, FeedId feedId, MemberId memberId) {
        var feed = feedReadService.findFeed(feedId);
        var thumbnailUrl = feed.getProfileImageUrl();
        service.addFeed(id, feedId, memberId, thumbnailUrl);
    }

    public void removeFeed(FeedCollectionId id, FeedId feedId, MemberId memberId) {
        service.removeFeed(id, feedId, memberId);
    }

}
