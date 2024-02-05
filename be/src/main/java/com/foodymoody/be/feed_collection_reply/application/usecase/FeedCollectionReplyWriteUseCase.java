package com.foodymoody.be.feed_collection_reply.application.usecase;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.application.service.FeedCollectionCommentReadService;
import com.foodymoody.be.feed_collection_comment.application.service.FeedCollectionCommentWriteService;
import com.foodymoody.be.feed_collection_reply.application.service.FeedCollectionReplyWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyWriteUseCase {

    private final FeedCollectionReplyWriteService replyService;
    private final FeedCollectionCommentReadService commentService;
    private final FeedCollectionCommentWriteService commentWriteService;

    @Transactional
    public FeedCollectionReplyId post(FeedCollectionCommentId commentId, Content content, MemberId memberId) {
        commentService.validateExistence(commentId);
        var id = replyService.post(commentId, content, memberId);
        commentWriteService.addReplyIds(commentId, id);
        return id;
    }

    public void delete(FeedCollectionReplyId replyId, MemberId memberId) {
        replyService.delete(replyId, memberId);
    }

    public void edit(FeedCollectionReplyId replyId, Content content, MemberId memberId) {
        replyService.edit(replyId, content, memberId);
    }
}
