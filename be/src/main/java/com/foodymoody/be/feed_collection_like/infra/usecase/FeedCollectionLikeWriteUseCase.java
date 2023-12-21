package com.foodymoody.be.feed_collection_like.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.application.FeedCollectionLikeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeWriteUseCase {

    private final FeedCollectionLikeWriteService likeService;

    public FeedCollectionLikeId post(FeedCollectionId feedCollectionId, MemberId memberId) {
        return likeService.post(feedCollectionId, memberId);
    }

    public void cancel(FeedCollectionLikeId likeId, MemberId memberId) {
        likeService.cancel(likeId, memberId);
    }
}
