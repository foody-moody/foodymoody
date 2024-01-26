package com.foodymoody.be.feed_reply_like_count.application.service;

import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.feed_reply_like_count.domain.ReplyHeartCount;
import com.foodymoody.be.feed_reply_like_count.domain.ReplyHeartCountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReplyHeartCountWriteService {

    private final ReplyHeartCountRepository repository;

    @Transactional
    public void increment(FeedReplyId feedReplyId) {
        repository.incrementCount(feedReplyId);
    }

    @Transactional
    public void decrement(FeedReplyId feedReplyId) {
        repository.decrementCount(feedReplyId);
    }

    @Transactional
    public void save(ReplyHeartCount replyHeartCount) {
        repository.save(replyHeartCount);
    }
}
