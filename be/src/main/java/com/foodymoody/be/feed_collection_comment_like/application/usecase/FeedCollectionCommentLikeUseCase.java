package com.foodymoody.be.feed_collection_comment_like.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment_like.application.service.FeedCollectionCommentLikeWriteService;
import com.foodymoody.be.feed_collection_comment_like_count.application.service.FeedCollectionCommentLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeUseCase {

    private final FeedCollectionCommentLikeWriteService likeService;
    private final FeedCollectionCommentLikeCountWriteService likeCountService;

    @Transactional
    public FeedCollectionCommentLikeId post(FeedCollectionCommentId commentId, MemberId memberId) {
        var likeId = likeService.post(commentId, memberId);
        likeCountService.increase(commentId);
        return likeId;
    }

    @Transactional
    public void cancel(FeedCollectionCommentId commentId, MemberId memberId) {
        likeService.cancel(commentId, memberId);
        likeCountService.decrease(commentId);
    }

}
