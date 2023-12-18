package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.infra.usecase.FeedCollectionWriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionWriteController {

    private final FeedCollectionWriteUseCase useCase;

    @PostMapping("/api/collections")
    public ResponseEntity<Void> createCollection(
            @RequestBody FeedCollectionCreateRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.createCollection(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
