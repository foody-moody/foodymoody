package com.foodymoody.be.feed_heart.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_heart.infra.persistence.jpa.FeedLikeJpaRepository;
import com.foodymoody.be.feed_like.domain.FeedLikeRepository;
import com.foodymoody.be.feed_like.domain.entity.FeedLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedLikeRepositoryImpl implements FeedLikeRepository {

    private final FeedLikeJpaRepository feedLikeJpaRepository;

    @Override
    public FeedLike save(FeedLike feedLike) {
        return feedLikeJpaRepository.save(feedLike);
    }

    @Override
    public boolean existsByMemberIdAndFeedId(MemberId memberId, FeedId feedId) {
        return feedLikeJpaRepository.existsByMemberIdAndFeedId(memberId, feedId);
    }

    @Override
    public void deleteByFeedIdAndMemberId(FeedId feedId, MemberId memberId) {
        feedLikeJpaRepository.deleteByFeedIdAndMemberId(feedId, memberId);
    }

}