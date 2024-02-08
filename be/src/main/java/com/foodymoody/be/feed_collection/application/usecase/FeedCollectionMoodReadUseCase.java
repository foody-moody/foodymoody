package com.foodymoody.be.feed_collection.application.usecase;

import com.foodymoody.be.feed_collection.application.service.FeedCollectionMoodReadService;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionMoodResponse;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionMoodReadUseCase {

    private final FeedCollectionMoodReadService service;

    public List<FeedCollectionMoodResponse> findAll() {
        List<FeedCollectionMood> moods = service.findAll();
        return moods.stream()
                .map(FeedCollectionMoodReadUseCase::toResponse)
                .collect(Collectors.toList());
    }

    private static FeedCollectionMoodResponse toResponse(FeedCollectionMood mood) {
        return new FeedCollectionMoodResponse(mood.getId(), mood.getName());
    }
}
