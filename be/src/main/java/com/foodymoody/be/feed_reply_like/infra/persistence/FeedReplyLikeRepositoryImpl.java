package com.foodymoody.be.feed_reply_like.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLike;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLikeRepository;
import com.foodymoody.be.feed_reply_like.infra.persistence.jpa.FeedReplyLikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedReplyLikeRepositoryImpl implements FeedReplyLikeRepository {

    private final FeedReplyLikeJpaRepository repository;

    @Override
    public FeedReplyLike save(FeedReplyLike feedReplyLike) {
        return repository.save(feedReplyLike);
    }

    @Override
    public void deleteByReplyIdAndMemberId(FeedReplyId feedReplyId, MemberId memberId) {
        repository.deleteByFeedReplyIdAndMemberId(feedReplyId, memberId);
    }

    @Override
    public boolean existsByReplyIdAndMemberId(FeedReplyId feedReplyId, MemberId memberId) {
        return repository.existsByFeedReplyIdAndMemberId(feedReplyId, memberId);
    }
}
