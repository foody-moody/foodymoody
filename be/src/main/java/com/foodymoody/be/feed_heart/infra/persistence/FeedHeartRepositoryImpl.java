package com.foodymoody.be.feed_heart.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_heart.domain.FeedHeartRepository;
import com.foodymoody.be.feed_heart.domain.entity.FeedHeart;
import com.foodymoody.be.feed_heart.infra.persistence.jpa.FeedHeartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedHeartRepositoryImpl implements FeedHeartRepository {

    private final FeedHeartJpaRepository feedHeartJpaRepository;

    @Override
    public FeedHeart save(FeedHeart feedHeart) {
        return feedHeartJpaRepository.save(feedHeart);
    }

    @Override
    public boolean existsHeartByMemberIdAndFeedId(MemberId memberId, FeedId feedId) {
        return feedHeartJpaRepository.existsHeartByMemberIdAndFeedId(memberId, feedId);
    }

    @Override
    public void deleteByFeedIdAndMemberId(FeedId feedId, MemberId memberId) {
        feedHeartJpaRepository.deleteByFeedIdAndMemberId(feedId, memberId);
    }

}
