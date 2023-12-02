package com.foodymoody.be.comment_heart_count.infra.presistence;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCount;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountRepository;
import com.foodymoody.be.comment_heart_count.infra.presistence.jpa.CommentHeartCountJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentHeartCountRepositoryImpl implements CommentHeartCountRepository {

    private final CommentHeartCountJpaRepository commentHeartCountJpaRepository;

    @Override
    public Optional<CommentHeartCount> findByCommentId(CommentId commentId) {
        return commentHeartCountJpaRepository.findByCommentId(commentId);
    }

    @Override
    public CommentHeartCount save(CommentHeartCount commentHeartCount) {
        return commentHeartCountJpaRepository.save(commentHeartCount);
    }
}
