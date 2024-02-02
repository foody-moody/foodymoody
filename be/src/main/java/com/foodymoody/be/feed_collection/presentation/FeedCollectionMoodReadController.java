package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.feed_collection.application.usecase.FeedCollectionMoodReadUseCase;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionMoodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionMoodReadController {

    private final FeedCollectionMoodReadUseCase useCase;

    @GetMapping("/api/feed_collections/moods")
    public ResponseEntity<Slice<FeedCollectionMoodResponse>> findAll(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = useCase.findAll(pageable);
        return ResponseEntity.ok()
                .body(response);
    }
}
