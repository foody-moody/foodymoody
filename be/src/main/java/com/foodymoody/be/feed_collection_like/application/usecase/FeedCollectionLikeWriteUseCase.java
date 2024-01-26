package com.foodymoody.be.feed_collection_like.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.application.service.FeedCollectionLikeWriteService;
import com.foodymoody.be.feed_collection_like_count.application.service.FeedCollectionLikeCountWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeWriteUseCase {

    private final FeedCollectionLikeWriteService likeService;
    private final FeedCollectionLikeCountWriteService likeCountService;

    @Transactional
    public FeedCollectionLikeId post(FeedCollectionId feedCollectionId, MemberId memberId) {
        var likeId = likeService.post(feedCollectionId, memberId);
        likeCountService.increaseLikeCount(feedCollectionId);
        return likeId;
    }

    @Transactional
    public void cancel(FeedCollectionLikeId likeId, FeedCollectionId feedCollectionId, MemberId memberId) {
        likeService.cancel(likeId, memberId);
        likeCountService.decreaseLikeCount(feedCollectionId);
    }
}
