package com.foodymoody.be.comment_heart_count.application;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCount;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountIdFactory;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentHeartCountWriteService {

    public static final long START_INDEX = 1L;
    private final CommentHeartCountRepository repository;

    @Transactional
    public void increment(CommentId commentId) {
        Optional<CommentHeartCount> heartCount = repository.findByCommentId(commentId);
        if (heartCount.isPresent()) {
            heartCount.get().increment();
        } else {
            repository.save(new CommentHeartCount(CommentHeartCountIdFactory.newId(), commentId, START_INDEX));
        }
    }

    @Transactional
    public void decrement(CommentId commentId) {
        Optional<CommentHeartCount> heartCount = repository.findByCommentId(commentId);
        if (heartCount.isPresent()) {
            heartCount.get().decrement();
        }
    }
}
