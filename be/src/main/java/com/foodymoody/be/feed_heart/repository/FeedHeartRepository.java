package com.foodymoody.be.feed_heart.repository;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed_heart.domain.FeedHeart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedHeartRepository extends JpaRepository<FeedHeart, String> {

    boolean existsHeartByMemberIdAndFeedId(String memberId, FeedId feedId);

    void deleteByFeedIdAndMemberId(FeedId feedId, String memberId);

}
