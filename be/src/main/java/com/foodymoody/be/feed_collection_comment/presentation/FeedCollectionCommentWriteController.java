package com.foodymoody.be.feed_collection_comment.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.CommentContent;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.infra.usecase.FeedCollectionCommentWriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionCommentWriteController {

    private final FeedCollectionCommentWriteUseCase useCase;


    @PostMapping("/api/feed_collections/{feedCollectionId}/comments")
    public ResponseEntity<Void> post(
            @PathVariable FeedCollectionId feedCollectionId,
            @RequestBody CommentContent content,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.post(feedCollectionId, content, memberId);
        return ResponseEntity.noContent().build();
    }

}
