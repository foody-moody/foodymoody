package com.foodymoody.be.feed_collection_comment_like.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment_like.application.usecase.FeedCollectionCommentLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionCommentLikeWriteController {

    private final FeedCollectionCommentLikeUseCase useCase;

    @PostMapping("/api/feed_collections/{ignoreFeedCollectionId}/comments/{commentId}/likes")
    public ResponseEntity<IdResponse> post(
            @PathVariable FeedCollectionId ignoreFeedCollectionId,
            @PathVariable FeedCollectionCommentId commentId,
            @CurrentMemberId MemberId memberId
    ) {
        var likeId = useCase.post(commentId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(likeId));
    }

    @DeleteMapping("/api/feed_collections/{ignoreFeedCollectionId}/comments/{commentId}/likes")
    public ResponseEntity<Void> cancel(
            @PathVariable FeedCollectionId ignoreFeedCollectionId,
            @PathVariable FeedCollectionCommentId commentId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.cancel(commentId, memberId);
        return ResponseEntity.noContent().build();
    }
}
