package com.foodymoody.be.feed_heart.repository;

import com.foodymoody.be.common.util.ids.FeedHeartId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_heart.domain.FeedHeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedHeartRepository extends JpaRepository<FeedHeart, FeedHeartId> {

    boolean existsHeartByMemberIdAndFeedId(MemberId memberId, FeedId feedId);

    void deleteByFeedIdAndMemberId(FeedId feedId, MemberId memberId);

}
