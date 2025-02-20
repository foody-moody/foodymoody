package com.foodymoody.be.feed_collection_reply_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;

public interface FeedCollectionReplyLikeRepository {

    FeedCollectionReplyLike save(FeedCollectionReplyLike like);

    void deleteByFeedCollectionReplyIdAndMemberId(FeedCollectionReplyId id, MemberId memberId);

}
