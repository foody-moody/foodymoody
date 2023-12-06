package com.foodymoody.be.feed_heart.repository;

import com.foodymoody.be.feed_heart.domain.FeedHeart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FeedHeartRepository extends JpaRepository<FeedHeart, String> {

    Optional<FeedHeart> findByFeedId(String feedId);

    boolean existsHeartByMemberIdAndFeedId(String memberId, String feedId);

    void deleteByFeedIdAndMemberId(String feedId, String memberId);

}
