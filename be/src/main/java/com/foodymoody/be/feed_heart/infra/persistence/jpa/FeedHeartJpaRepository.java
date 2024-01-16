package com.foodymoody.be.feed_heart.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedHeartId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_heart.domain.entity.FeedHeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedHeartJpaRepository extends JpaRepository<FeedHeart, FeedHeartId> {

    boolean existsHeartByMemberIdAndFeedId(MemberId memberId, FeedId feedId);

    void deleteByFeedIdAndMemberId(FeedId feedId, MemberId memberId);

}
