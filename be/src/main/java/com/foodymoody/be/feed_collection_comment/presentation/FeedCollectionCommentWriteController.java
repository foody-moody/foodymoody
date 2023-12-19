package com.foodymoody.be.feed_collection_comment.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.CommentContent;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.infra.usecase.FeedCollectionCommentWriteUseCase;
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
public class FeedCollectionCommentWriteController {

    private final FeedCollectionCommentWriteUseCase useCase;

    @PostMapping("/api/collections/{feedCollectionId}/comments")
    public ResponseEntity<IdResponse> post(
            @PathVariable FeedCollectionId feedCollectionId,
            @RequestBody CommentContent content,
            @CurrentMemberId MemberId memberId
    ) {
        var id = useCase.post(feedCollectionId, content, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @DeleteMapping("/api/collections/{feedCollectionId}/comments/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable FeedCollectionId feedCollectionId,
            @PathVariable FeedCollectionCommentId id,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.delete(feedCollectionId, id, memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/collections/{ignoredFeedCollectionId}/comments/{id}")
    public ResponseEntity<Void> edit(
            @PathVariable FeedCollectionId ignoredFeedCollectionId,
            @PathVariable FeedCollectionCommentId id,
            @RequestBody CommentContent content,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.edit(id, content, memberId);
        return ResponseEntity.noContent().build();
    }
}
