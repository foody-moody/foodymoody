package com.foodymoody.be.feed_collection_reply.infra.usecase;

import com.foodymoody.be.common.util.CommentContent;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.application.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_reply.application.FeedCollectionReplyWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyWriteUseCase {

    private final FeedCollectionReplyWriteService replyService;
    private final FeedCollectionCommentReadService commentService;

    public FeedCollectionReplyId post(FeedCollectionCommentId commentId, CommentContent content, MemberId memberId) {
        commentService.validateExistence(commentId);
        return replyService.post(commentId, content, memberId);
    }

    public void delete(FeedCollectionReplyId replyId, MemberId memberId) {
        replyService.delete(replyId, memberId);
    }

    public void edit(FeedCollectionReplyId replyId, CommentContent content, MemberId memberId) {
        replyService.edit(replyId, content, memberId);
    }
}
