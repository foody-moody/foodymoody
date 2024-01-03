package com.foodymoody.be.reply_heart_count.application;

import com.foodymoody.be.common.util.ids.ReplyId;
import com.foodymoody.be.reply_heart_count.domain.ReplyHeartCount;
import com.foodymoody.be.reply_heart_count.domain.ReplyHeartCountRepository;
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
    public void increment(ReplyId replyId) {
        repository.incrementCount(replyId);
    }

    @Transactional
    public void decrement(ReplyId replyId) {
        repository.decrementCount(replyId);
    }

    @Transactional
    public void save(ReplyHeartCount replyHeartCount) {
        repository.save(replyHeartCount);
    }
}
