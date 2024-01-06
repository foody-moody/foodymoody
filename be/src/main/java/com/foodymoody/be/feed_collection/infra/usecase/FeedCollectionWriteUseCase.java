package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed_collection.application.FeedCollectionMoodWriteService;
import com.foodymoody.be.feed_collection.application.FeedCollectionWriterService;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionCreateRequest;
import com.foodymoody.be.feed_collection.infra.usecase.dto.FeedCollectionEditRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionWriteUseCase {

    private final FeedCollectionWriterService service;
    private final FeedReadService feedReadService;
    private final FeedCollectionMoodWriteService moodService;

    public FeedCollectionId create(FeedCollectionCreateRequest request, MemberId memberId) {
        var moods = moodService.findAllById(request.getMoodIds());
        return service.create(
                request.getTitle(),
                request.getDescription(),
                request.getThumbnailUrl(),
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
                request.getThumbnailUrl(),
                moods,
                memberId
        );
    }

    public void update(FeedCollectionId id, List<FeedId> feedIds, MemberId memberId) {
        feedReadService.validateIds(feedIds);
        service.update(id, feedIds, memberId);
    }

    public void delete(FeedCollectionId id, MemberId memberId) {
        service.delete(id, memberId);
    }
}
