package com.foodymoody.be.feed_collection_comment_like.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment_like.infra.usecase.FeedCollectionCommentLikeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionCommentLikeWriteController {

    private final FeedCollectionCommentLikeUseCase useCase;

    @PostMapping("/api/feed_collections_comments/{commentId}/likes")
    public ResponseEntity<IdResponse> like(
            @PathVariable FeedCollectionCommentId commentId, @CurrentMemberId MemberId memberId
    ) {
        var likeId = useCase.like(commentId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(likeId));
    }
}
