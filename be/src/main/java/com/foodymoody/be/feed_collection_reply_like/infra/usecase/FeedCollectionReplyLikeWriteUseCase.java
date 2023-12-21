package com.foodymoody.be.feed_collection_reply_like.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply_like.application.FeedCollectionReplyLikeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeWriteUseCase {

    private final FeedCollectionReplyLikeWriteService likeService;

    public FeedCollectionReplyLikeId post(FeedCollectionReplyId replyId, MemberId memberId) {
        return likeService.post(replyId, memberId);
    }

    public void cancel(MemberId memberId, FeedCollectionReplyLikeId id) {
        likeService.cancel(memberId, id);
    }
}
