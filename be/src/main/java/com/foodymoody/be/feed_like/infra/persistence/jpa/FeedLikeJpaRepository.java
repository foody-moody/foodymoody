package com.foodymoody.be.feed_like.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_like.domain.entity.FeedLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedLikeJpaRepository extends JpaRepository<FeedLike, FeedLikeId> {

    boolean existsByMemberIdAndFeedId(MemberId memberId, FeedId feedId);

    void deleteByFeedIdAndMemberId(FeedId feedId, MemberId memberId);

}
