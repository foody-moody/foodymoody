package com.foodymoody.be.feed_comment_like.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLike;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLikeRepository;
import com.foodymoody.be.feed_comment_like.infra.persistence.jpa.FeedCommentLikeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCommentLikeRepositoryImpl implements FeedCommentLikeRepository {

    private final FeedCommentLikeJpaRepository feedCommentLikeJpaRepository;

    @Override
    public FeedCommentLike save(FeedCommentLike feedCommentLike) {
        return feedCommentLikeJpaRepository.save(feedCommentLike);
    }

    @Override
    public void deleteByCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId) {
        feedCommentLikeJpaRepository.deleteByFeedCommentIdAndMemberId(feedCommentId, memberId);
    }

    @Override
    public boolean existsByFeedCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId) {
        return feedCommentLikeJpaRepository.existsByFeedCommentIdAndMemberId(feedCommentId, memberId);
    }
}
