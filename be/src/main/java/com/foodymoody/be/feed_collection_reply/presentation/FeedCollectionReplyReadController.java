package com.foodymoody.be.feed_collection_reply.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.infra.usecase.FeedCollectionReplyReadUseCase;
import com.foodymoody.be.feed_collection_reply.infra.usecase.FeedCollectionReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCollectionReplyReadController {

    private final FeedCollectionReplyReadUseCase useCase;

    @GetMapping("/api/feed_collections_comments/{commentId}/replies")
    public ResponseEntity<Slice<FeedCollectionReplyResponse>> fetch(
            @PathVariable FeedCollectionCommentId commentId,
            @CurrentMemberId MemberId memberId,
            @PageableDefault(sort = "createdAt", direction = Direction.DESC) Pageable pageable
    ) {
        if (memberId == null) {
            var replaySummaries = useCase.fetch(commentId, pageable);
            return ResponseEntity.ok(replaySummaries);
        }
        var replaySummaries = useCase.fetch(commentId, memberId, pageable);
        return ResponseEntity.ok(replaySummaries);
    }
}
