package com.foodymoody.be.feed_collection.application.usecase;

import com.foodymoody.be.feed_collection.application.service.FeedCollectionMoodReadService;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionMoodResponse;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionMoodReadUseCase {

    private final FeedCollectionMoodReadService service;

    public Slice<FeedCollectionMoodResponse> findAll(Pageable pageable) {
        Slice<FeedCollectionMood> moods = service.findAll(pageable);
        return moods.map(FeedCollectionMoodReadUseCase::toResponse);
    }

    private static FeedCollectionMoodResponse toResponse(FeedCollectionMood mood) {
        return new FeedCollectionMoodResponse(mood.getId(), mood.getName());
    }
}
