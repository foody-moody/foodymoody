package com.foodymoody.be.feed_comment_like.application.service;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment_like.application.exception.FeedCommentIsAlreadyLikedException;
import com.foodymoody.be.feed_comment_like.application.exception.FeedCommentIsNotLikedException;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLike;
import com.foodymoody.be.feed_comment_like.domain.FeedCommentLikeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentLikeWriteService {

    private final FeedCommentLikeRepository repository;

    @Transactional
    public FeedCommentLike register(FeedCommentId feedCommentId, MemberId memberId) {
        if (existsByCommentIdAndMemberId(feedCommentId, memberId)) {
            throw new FeedCommentIsAlreadyLikedException();
        }
        var feedCommentLikeId = IdFactory.createFeedCommentLikeId();
        var createdAt = LocalDateTime.now();
        var feedCommentLike = new FeedCommentLike(feedCommentLikeId, feedCommentId, memberId, createdAt);
        return repository.save(feedCommentLike);
    }

    @Transactional
    public void delete(FeedCommentId feedCommentId, MemberId memberId) {
        if (!existsByCommentIdAndMemberId(feedCommentId, memberId)) {
            throw new FeedCommentIsNotLikedException();
        }
        repository.deleteByCommentIdAndMemberId(feedCommentId, memberId);
    }

    private boolean existsByCommentIdAndMemberId(FeedCommentId feedCommentId, MemberId memberId) {
        return repository.existsByFeedCommentIdAndMemberId(feedCommentId, memberId);
    }
}
