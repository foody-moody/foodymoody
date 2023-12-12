package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.feed_collection.infra.usecase.FeedCollectionReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionReadController {

    private final FeedCollectionReadUseCase useCase;

    @GetMapping("/api/collections")
    public ResponseEntity<Slice<FeedCollectionSummary>> fetchCollection(
            @PageableDefault(size = 20, sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        var feedCollections = useCase.fetchCollection(pageable);
        return ResponseEntity.ok(feedCollections);
    }
}
