package com.foodymoody.be.feed_collection_comment_like.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionCommentLikeJpaRepository extends
        JpaRepository<FeedCollectionCommentLike, FeedCollectionCommentLikeId> {

    void deleteByCommentIdAndMemberId(FeedCollectionCommentId commentId, MemberId memberId);

}
