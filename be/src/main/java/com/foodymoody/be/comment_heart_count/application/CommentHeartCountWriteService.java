package com.foodymoody.be.comment_heart_count.application;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCount;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentHeartCountWriteService {

    private final CommentHeartCountRepository repository;

    @Transactional
    public void increment(CommentId commentId) {
        repository.incrementCount(commentId);
    }

    @Transactional
    public void decrement(CommentId commentId) {
        repository.decrementCount(commentId);
    }

    @Transactional
    public void save(CommentHeartCount commentHeartCount) {
        repository.save(commentHeartCount);
    }
}
