package com.foodymoody.be.feed_comment_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCount;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCountRepository;
import com.foodymoody.be.feed_comment_like_count.infra.persistence.jpa.FeedCommentLikeCountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCommentLikeCountRepositoryImpl implements FeedCommentLikeCountRepository {

    private final FeedCommentLikeCountJpaRepository feedCommentLikeCountJpaRepository;

    @Override
    public FeedCommentLikeCount save(FeedCommentLikeCount feedCommentLikeCount) {
        return feedCommentLikeCountJpaRepository.save(feedCommentLikeCount);
    }

    @Override
    public void incrementCount(FeedCommentId feedCommentId) {
        feedCommentLikeCountJpaRepository.incrementCount(feedCommentId);
    }

    @Override
    public void decrementCount(FeedCommentId feedCommentId) {
        feedCommentLikeCountJpaRepository.decrementCount(feedCommentId);
    }
}
