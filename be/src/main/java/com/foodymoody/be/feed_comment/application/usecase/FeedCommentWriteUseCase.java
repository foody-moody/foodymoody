package com.foodymoody.be.feed_comment.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedCommentData;
import com.foodymoody.be.feed_comment.application.service.FeedCommentWriteService;
import com.foodymoody.be.feed_comment.application.service.FeedReplyReadService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class FeedCommentWriteUseCase {

    private final FeedReadService feedService;
    private final FeedCommentWriteService feedCommentService;
    private final FeedReplyReadService feedReplyService;

    public FeedCommentId registerComment(RegisterFeedCommentData data) {
        feedService.validateId(data.getFeedId());
        return feedCommentService.register(data);
    }

    @Transactional
    public void deleteReply(FeedCommentId commentId, FeedReplyId replyId, MemberId memberId) {
        var feedReply = feedReplyService.fetchById(replyId);
        LocalDateTime updatedAt = LocalDateTime.now();
        feedReply.delete(memberId, updatedAt);
        feedCommentService.deleteReply(commentId, feedReply, updatedAt);
    }

}
