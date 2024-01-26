package com.foodymoody.be.feed_reply_like.application.service;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLike;
import com.foodymoody.be.feed_reply_like.domain.FeedReplyLikeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedReplyLikeWriteService {

    private final FeedReplyLikeRepository feedReplyLikeRepository;

    @Transactional
    public void registerReplyLike(FeedCommentId feedCommentId, FeedReplyId feedReplyId, MemberId memberId) {
        var feedReplyLikeId = IdFactory.createFeedReplyLikeId();
        var now = LocalDateTime.now();
        var feedReplyLike = new FeedReplyLike(feedReplyLikeId, feedCommentId, feedReplyId, memberId, now);
        feedReplyLikeRepository.save(feedReplyLike);
    }

    @Transactional
    public void deleteReplyLike(FeedReplyId feedReplyId, MemberId memberId) {
        feedReplyLikeRepository.deleteByReplyIdAndMemberId(feedReplyId, memberId);
    }

    public boolean existsByReplyIdAndMemberId(FeedReplyId feedReplyId, MemberId memberId) {
        return feedReplyLikeRepository.existsByReplyIdAndMemberId(feedReplyId, memberId);
    }
}
