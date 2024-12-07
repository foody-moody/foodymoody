package com.foodymoody.be.feed_reply_like.presentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_reply_like.application.usecase.FeedReplyLikeWriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedReplyLikeWriteController {

    private final FeedReplyLikeWriteUseCase useCase;

    @PostMapping("/api/feed/{ignoreFeedId}/comments/{feedCommentId}/replies/{feedReplyId}/likes")
    public ResponseEntity<Void> create(
            @PathVariable FeedId ignoreFeedId,
            @PathVariable FeedCommentId feedCommentId,
            @PathVariable FeedReplyId feedReplyId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.register(feedCommentId, feedReplyId, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/feed/{ignoreFeedId}/comments/{ignoredFeedCommentId}/replies/{feedReplyId}/likes")
    public ResponseEntity<Void> delete(
            @PathVariable FeedId ignoreFeedId,
            @PathVariable FeedCommentId ignoredFeedCommentId,
            @PathVariable FeedReplyId feedReplyId,
            @CurrentMemberId MemberId memberId
    ) {
        useCase.delete(feedReplyId, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
