package com.foodymoody.be.feed_comment_like.domain;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;

public interface FeedCommentLikeRepository {

    FeedCommentLike save(FeedCommentLike feedCommentLike);

    void deleteByCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId);

    boolean existsByFeedCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId);

}
