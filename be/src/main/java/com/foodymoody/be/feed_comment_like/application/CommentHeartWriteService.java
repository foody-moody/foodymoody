package com.foodymoody.be.feed_comment_like.application;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedCommentLikeId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLike;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLikeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentHeartWriteService {

    private final FeedCommentLikeRepository feedCommentLikeRepository;

    @Transactional
    public FeedCommentLike registerCommentHeart(FeedCommentId feedCommentId, MemberId memberId) {
        FeedCommentLikeId feedCommentLikeId = IdFactory.createFeedCommentLikeId();
        LocalDateTime now = LocalDateTime.now();
        FeedCommentLike feedCommentLike = new FeedCommentLike(feedCommentLikeId, feedCommentId, memberId, now, now);
        return feedCommentLikeRepository.save(feedCommentLike);
    }

    @Transactional
    public void deleteCommentHeart(FeedCommentId feedCommentId, MemberId memberId) {
        feedCommentLikeRepository.deleteByCommentIdAndMemberId(feedCommentId, memberId);
    }

    public boolean existsByCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId) {
        return feedCommentLikeRepository.existsByCommentIdAndMemberId(feedCommentId, memberId);
    }
}
