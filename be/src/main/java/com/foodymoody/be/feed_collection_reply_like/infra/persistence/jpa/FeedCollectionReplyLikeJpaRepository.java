package com.foodymoody.be.feed_collection_reply_like.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply_like.domain.FeedCollectionReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionReplyLikeJpaRepository extends
        JpaRepository<FeedCollectionReplyLike, FeedCollectionReplyLikeId> {

    void deleteByFeedCollectionReplyIdAndMemberId(FeedCollectionReplyId id, MemberId memberId);

}
