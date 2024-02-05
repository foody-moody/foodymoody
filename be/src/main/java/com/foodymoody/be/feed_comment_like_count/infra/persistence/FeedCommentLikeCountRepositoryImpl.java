package com.foodymoody.be.feed_comment_like_count.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCount;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCountRepository;
import com.foodymoody.be.feed_comment_like_count.infra.persistence.jpa.CommentHeartCountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCommentLikeCountRepositoryImpl implements FeedCommentLikeCountRepository {

    private final CommentHeartCountJpaRepository commentHeartCountJpaRepository;

    @Override
    public FeedCommentLikeCount save(FeedCommentLikeCount feedCommentLikeCount) {
        return commentHeartCountJpaRepository.save(feedCommentLikeCount);
    }

    @Override
    public void incrementCount(FeedCommentId feedCommentId) {
        commentHeartCountJpaRepository.incrementCount(feedCommentId);
    }

    @Override
    public void decrementCount(FeedCommentId feedCommentId) {
        commentHeartCountJpaRepository.decrementCount(feedCommentId);
    }
}
