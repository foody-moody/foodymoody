package com.foodymoody.be.feed_collection_reply.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.application.usecase.FeedCollectionReplyWriteUseCase;
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
public class FeedCollectionReplyWriteController {

    private final FeedCollectionReplyWriteUseCase useCase;

    @PostMapping("/api/feed_collections/{ignoreFeedCollectionId}/comments/{commentId}/replies")
    public ResponseEntity<IdResponse> post(
            @PathVariable FeedCollectionCommentId ignoreFeedCollectionId,
            @PathVariable FeedCollectionCommentId commentId,
            @RequestBody Content content,
            @CurrentMemberId MemberId memberId
    ) {
        var id = useCase.post(commentId, content, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @DeleteMapping("/api/feed_collections/{ignoreFeedCollectionId}/comments/{ignoredCommentId}/replies/{replyId}")
    public ResponseEntity<Void> delete(
            @PathVariable FeedCollectionCommentId ignoreFeedCollectionId,
            @PathVariable FeedCollectionCommentId ignoredCommentId,
            @PathVariable FeedCollectionReplyId replyId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.delete(replyId, memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/feed_collections/{ignoreFeedCollectionId}/comments/{ignoredCommentId}/replies/{replyId}")
    public ResponseEntity<Void> edit(
            @PathVariable FeedCollectionCommentId ignoreFeedCollectionId,
            @PathVariable FeedCollectionCommentId ignoredCommentId,
            @PathVariable FeedCollectionReplyId replyId,
            @RequestBody Content content,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.edit(replyId, content, memberId);
        return ResponseEntity.noContent().build();
    }

}
