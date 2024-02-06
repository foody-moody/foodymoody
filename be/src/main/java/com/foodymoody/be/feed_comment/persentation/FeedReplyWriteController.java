package com.foodymoody.be.feed_comment.persentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.service.FeedReplyWriteService;
import com.foodymoody.be.feed_comment.persentation.dto.UpdateFeedReplyRequest;
import com.foodymoody.be.feed_comment.persentation.translator.FeedReplyTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class FeedReplyWriteController {

    private final FeedReplyWriteService service;

    @PutMapping("/api/feed/{ignoreFeedId}/comments/{ignoreFeedCommentId}/replies/{feedReplyId}")
    public ResponseEntity<Void> update(
            @PathVariable FeedId ignoreFeedId,
            @PathVariable FeedCommentId ignoreFeedCommentId,
            @PathVariable FeedReplyId feedReplyId,
            @RequestBody UpdateFeedReplyRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var data = FeedReplyTranslator.toUpdateReplyData(request, feedReplyId, memberId);
        service.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/feed/{ignoreFeedId}/comments/{ignoreFeedCommentId}/replies/{feedReplyId}")
    public ResponseEntity<Void> delete(
            @PathVariable FeedId ignoreFeedId,
            @PathVariable FeedCommentId ignoreFeedCommentId,
            @PathVariable FeedReplyId feedReplyId,
            @CurrentMemberId MemberId memberId
    ) {
        service.delete(feedReplyId, memberId);
        return ResponseEntity.ok().build();
    }
}
