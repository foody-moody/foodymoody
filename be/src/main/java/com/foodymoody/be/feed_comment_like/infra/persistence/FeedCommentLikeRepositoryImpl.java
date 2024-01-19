package com.foodymoody.be.feed_comment_like.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLike;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLikeRepository;
import com.foodymoody.be.feed_comment_like.infra.persistence.jpa.CommentHeartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCommentLikeRepositoryImpl implements FeedCommentLikeRepository {

    private final CommentHeartJpaRepository commentHeartJpaRepository;

    @Override
    public FeedCommentLike save(FeedCommentLike feedCommentLike) {
        return commentHeartJpaRepository.save(feedCommentLike);
    }

    @Override
    public void deleteByCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId) {
        commentHeartJpaRepository.deleteByFeedCommentIdAndMemberId(feedCommentId, memberId);
    }

    @Override
    public boolean existsByCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId) {
        return commentHeartJpaRepository.existsByFeedCommentIdAndMemberId(feedCommentId, memberId);
    }
}
