package com.foodymoody.be.feed_collection.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.application.usecase.FeedCollectionWriteUseCase;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionAddFeedRequest;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionCreateRequest;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionEditRequest;
import com.foodymoody.be.feed_collection.application.usecase.dto.FeedCollectionFeedsUpdateRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionWriteController {

    private final FeedCollectionWriteUseCase useCase;

    @PostMapping("/api/feed_collections")
    public ResponseEntity<IdResponse> create(
            @Valid @RequestBody FeedCollectionCreateRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var id = useCase.create(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @PutMapping("/api/feed_collections/{id}")
    public ResponseEntity<Void> edit(
            @PathVariable FeedCollectionId id,
            @RequestBody FeedCollectionEditRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.edit(id, request, memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/feed_collections/{id}/feeds")
    public ResponseEntity<Void> updateFeed(
            @PathVariable FeedCollectionId id,
            @RequestBody FeedCollectionFeedsUpdateRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.update(id, request, memberId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/feed_collections/{id}/feeds")
    public ResponseEntity<Void> addFeed(
            @PathVariable FeedCollectionId id,
            @RequestBody FeedCollectionAddFeedRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.addFeed(id, request.getFeedId(), memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/feed_collections/{id}/feeds/{feedId}")
    public ResponseEntity<Void> removeFeed(
            @PathVariable FeedCollectionId id,
            @PathVariable FeedId feedId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.removeFeed(id, feedId, memberId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/feed_collections/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable FeedCollectionId id,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.delete(id, memberId);
        return ResponseEntity.noContent().build();
    }
}
