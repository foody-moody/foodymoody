package com.foodymoody.be.feed_reply_like_count.application.service;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.feed_reply_like_count.domain.FeedReplyLikeCount;
import com.foodymoody.be.feed_reply_like_count.domain.FeedReplyLikeCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedReplyLikeCountWriteService {

    private final FeedReplyLikeCountRepository repository;

    @Transactional
    public void increment(FeedReplyId feedReplyId) {
        repository.incrementCount(feedReplyId);
    }

    @Transactional
    public void decrement(FeedReplyId feedReplyId) {
        repository.decrementCount(feedReplyId);
    }

    @Transactional
    public void save(FeedReplyLikeCount feedReplyLikeCount) {
        repository.save(feedReplyLikeCount);
    }

}
