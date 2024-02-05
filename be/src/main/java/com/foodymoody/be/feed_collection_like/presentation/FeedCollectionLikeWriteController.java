package com.foodymoody.be.feed_collection_like.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.application.usecase.FeedCollectionLikeWriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionLikeWriteController {

    private final FeedCollectionLikeWriteUseCase useCase;

    @PostMapping("/api/feed_collections/{feedCollectionId}/likes")
    public ResponseEntity<IdResponse> post(
            @PathVariable FeedCollectionId feedCollectionId,
            @CurrentMemberId MemberId memberId
    ) {
        FeedCollectionLikeId id = useCase.post(feedCollectionId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @DeleteMapping("/api/feed_collections/{feedCollectionId}/likes/{likeId}")
    public ResponseEntity<Void> cancel(
            @PathVariable FeedCollectionId feedCollectionId,
            @PathVariable FeedCollectionLikeId likeId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.cancel(likeId, feedCollectionId, memberId);
        return ResponseEntity.noContent().build();
    }
}
