package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.feed_collection.infra.usecase.CollectionReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionReadController {

    private final CollectionReadUseCase useCase;

    @GetMapping("/api/collections")
    public ResponseEntity<Object> fetchCollection(@MemberId String memberId) {
        useCase.fetchCollection(memberId);
        return ResponseEntity.ok().build();
    }
}
