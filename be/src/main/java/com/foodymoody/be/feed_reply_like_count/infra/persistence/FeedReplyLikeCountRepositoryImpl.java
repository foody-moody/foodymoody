package com.foodymoody.be.feed_reply_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.feed_reply_like_count.domain.FeedReplyLikeCount;
import com.foodymoody.be.feed_reply_like_count.domain.FeedReplyLikeCountRepository;
import com.foodymoody.be.feed_reply_like_count.infra.persistence.jpa.FeedReplyLikeCountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedReplyLikeCountRepositoryImpl implements FeedReplyLikeCountRepository {

    private final FeedReplyLikeCountJpaRepository feedReplyLikeCountJpaRepository;

    @Override
    public FeedReplyLikeCount save(FeedReplyLikeCount feedReplyLikeCount) {
        return feedReplyLikeCountJpaRepository.save(feedReplyLikeCount);
    }

    @Override
    public void incrementCount(FeedReplyId feedReplyId) {
        feedReplyLikeCountJpaRepository.incrementCount(feedReplyId);
    }

    @Override
    public void decrementCount(FeedReplyId feedReplyId) {
        feedReplyLikeCountJpaRepository.decrementCount(feedReplyId);
    }
}
