package com.foodymoody.be.feed_comment_like.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment_like.application.usecase.FeedCommentLikeWriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCommentLikeWriteController {

    private final FeedCommentLikeWriteUseCase useCase;

    @PostMapping("/api/feed/{ignoreFeedId}/comments/{feedCommentId}/likes")
    public ResponseEntity<Void> create(
            @PathVariable FeedId ignoreFeedId,
            @PathVariable FeedCommentId feedCommentId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.registerCommentLike(feedCommentId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/feed/{ignoreFeedId}/comments/{feedCommentId}/likes")
    public ResponseEntity<Void> delete(
            @PathVariable FeedId ignoreFeedId,
            @PathVariable FeedCommentId feedCommentId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.deleteCommentLike(feedCommentId, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
