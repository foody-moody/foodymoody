package com.foodymoody.be.feed_comment_like_count.application.service;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCount;
import com.foodymoody.be.feed_comment_like_count.domain.FeedCommentLikeCountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedCommentLikeCountWriteService {

    private final FeedCommentLikeCountRepository repository;

    @Transactional
    public void increment(FeedCommentId feedCommentId) {
        repository.incrementCount(feedCommentId);
    }

    @Transactional
    public void decrement(FeedCommentId feedCommentId) {
        repository.decrementCount(feedCommentId);
    }

    @Transactional
    public void save(FeedCommentLikeCount feedCommentLikeCount) {
        repository.save(feedCommentLikeCount);
    }
}
