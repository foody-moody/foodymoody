package com.foodymoody.be.comment_heart_count.infra.persistence;

import com.foodymoody.be.comment_heart_count.domain.CommentHeartCount;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountRepository;
import com.foodymoody.be.comment_heart_count.infra.persistence.jpa.CommentHeartCountJpaRepository;
import com.foodymoody.be.common.util.ids.CommentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentHeartCountRepositoryImpl implements CommentHeartCountRepository {

    private final CommentHeartCountJpaRepository commentHeartCountJpaRepository;

    @Override
    public CommentHeartCount save(CommentHeartCount commentHeartCount) {
        return commentHeartCountJpaRepository.save(commentHeartCount);
    }

    @Override
    public void incrementCount(CommentId commentId) {
        commentHeartCountJpaRepository.incrementCount(commentId);
    }

    @Override
    public void decrementCount(CommentId commentId) {
        commentHeartCountJpaRepository.decrementCount(commentId);
    }
}
