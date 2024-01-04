package com.foodymoody.be.feed_collection_reply_like.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply_like.application.FeedCollectionReplyLikeWriteService;
import com.foodymoody.be.feed_collection_reply_like_count.application.FeedCollectionReplyLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeWriteUseCase {

    private final FeedCollectionReplyLikeWriteService likeService;
    private final FeedCollectionReplyLikeCountWriteService countService;

    public FeedCollectionReplyLikeId post(FeedCollectionReplyId replyId, MemberId memberId) {
        var likeId = likeService.post(replyId, memberId);
        countService.increaseCount(replyId);
        return likeId;
    }

    public void cancel(FeedCollectionReplyId replyId, MemberId memberId, FeedCollectionReplyLikeId id) {
        likeService.cancel(memberId, id);
        countService.decreaseCount(replyId);
    }
}
