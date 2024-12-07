package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.feed_collection.application.usecase.FeedCollectionMoodReadUseCase;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionMoodResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionMoodReadController {

    private final FeedCollectionMoodReadUseCase useCase;

    @GetMapping("/api/feed_collections/moods")
    public ResponseEntity<List<FeedCollectionMoodResponse>> findAll() {
        var response = useCase.findAll();
        return ResponseEntity.ok()
                .body(response);
    }

}
