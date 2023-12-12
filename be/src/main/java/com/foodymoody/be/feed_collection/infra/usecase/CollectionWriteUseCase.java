package com.foodymoody.be.feed_collection.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed_collection.application.FeedCollectionWriterService;
import com.foodymoody.be.feed_collection.presentation.FeedCollectionCreateRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CollectionWriteUseCase {

    private final FeedCollectionWriterService service;
    private final FeedReadService feedReadService;

    public void createCollection(FeedCollectionCreateRequest request, String memberIdValue) {
        MemberId memberId = IdFactory.createMemberId(memberIdValue);
        List<FeedId> feedIds = request.getFeedIds()
                .stream()
                .map(IdFactory::createFeedId)
                .collect(Collectors.toList());
        feedReadService.validateIds(feedIds);
        service.createCollection(request.getTitle(), request.getDescription(), request.getThumbnailUrl(),
                request.isPrivate(), memberId, feedIds);
    }
}
