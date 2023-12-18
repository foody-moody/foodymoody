package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed_collection.application.FeedCollectionWriterService;
import com.foodymoody.be.feed_collection.presentation.FeedCollectionCreateRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionWriteUseCase {

    private final FeedCollectionWriterService service;
    private final FeedReadService feedReadService;

    public void createCollection(FeedCollectionCreateRequest request, MemberId memberId) {
        List<FeedId> feedIds = request.getFeedIds();
        feedReadService.validateIds(feedIds);
        createCollection(request, memberId, feedIds);
    }

    private void createCollection(FeedCollectionCreateRequest request, MemberId memberId, List<FeedId> feedIds) {
        service.createCollection(request.getTitle(), request.getDescription(), request.getThumbnailUrl(),
                                 request.isPrivate(), memberId, feedIds
        );
    }
}
