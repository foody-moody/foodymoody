package com.foodymoody.be.feed_collection_reply_like.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply_like.application.service.FeedCollectionReplyLikeWriteService;
import com.foodymoody.be.feed_collection_reply_like_count.application.service.FeedCollectionReplyLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeWriteUseCase {

    private final FeedCollectionReplyLikeWriteService likeService;
    private final FeedCollectionReplyLikeCountWriteService countService;

    @Transactional
    public FeedCollectionReplyLikeId post(
            FeedCollectionReplyId replyId,
            FeedCollectionCommentId commentId,
            MemberId memberId
    ) {
        var likeId = likeService.post(replyId, commentId, memberId);
        countService.increaseCount(replyId);
        return likeId;
    }

    @Transactional
    public void cancel(FeedCollectionReplyId replyId, MemberId memberId) {
        likeService.cancel(memberId, replyId);
        countService.decreaseCount(replyId);
    }

}
