package com.foodymoody.be.feed_reply_like.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.FeedReplyLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyHeartJpaRepository extends JpaRepository<FeedReplyLike, FeedReplyLikeId> {

    void deleteByFeedReplyIdAndMemberId(FeedReplyId feedReplyId, MemberId memberId);

    boolean existsByFeedReplyIdAndMemberId(FeedReplyId feedReplyId, MemberId memberId);
}
