package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.common.annotation.MemberId;
import com.foodymoody.be.feed_collection.infra.usecase.CollectionWriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionWriteController {

    private final CollectionWriteUseCase useCase;

    @PostMapping("/api/collections")
    public ResponseEntity<Void> createCollection(@RequestBody FeedCollectionCreateRequest request,
            @MemberId String memberId) {
        useCase.createCollection(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
