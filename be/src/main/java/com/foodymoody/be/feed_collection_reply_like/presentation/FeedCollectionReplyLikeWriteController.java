package com.foodymoody.be.feed_collection_reply_like.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply_like.infra.usecase.FeedCollectionReplyLikeWriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionReplyLikeWriteController {

    private final FeedCollectionReplyLikeWriteUseCase useCase;

    @PostMapping("/api/feed_collections_comments/{ignoredCommentId}/replies/{replyId}/likes")
    public ResponseEntity<IdResponse> post(
            @PathVariable FeedCollectionCommentId ignoredCommentId,
            @PathVariable FeedCollectionReplyId replyId,
            @CurrentMemberId MemberId memberId
    ) {
        var id = useCase.post(replyId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @DeleteMapping("/api/feed_collections_comments/{ignoredCommentId}/replies/{replyId}/likes/{id}")
    public ResponseEntity<Void> cancel(
            @PathVariable FeedCollectionCommentId ignoredCommentId,
            @PathVariable FeedCollectionReplyId replyId,
            @PathVariable FeedCollectionReplyLikeId id,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.cancel(replyId, memberId, id);
        return ResponseEntity.noContent().build();
    }
}
