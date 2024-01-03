package com.foodymoody.be.feed_collection_comment_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;

public interface FeedCollectionCommentLikeRepository {

    FeedCollectionCommentLike save(FeedCollectionCommentLike commentLike);

    void deleteByCommentIdAndMemberId(FeedCollectionCommentId commentId, MemberId memberId);
}
