package com.foodymoody.be.feed_collection_comment_like.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment_like.application.FeedCollectionCommentLikeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeUseCase {

    private final FeedCollectionCommentLikeWriteService likeService;

    public FeedCollectionCommentLikeId like(FeedCollectionCommentId commentId, MemberId memberId) {
        return likeService.like(commentId, memberId);
    }

    public void cancel(FeedCollectionCommentId commentId, MemberId memberId) {
        likeService.cancel(commentId, memberId);
    }
}
