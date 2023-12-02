package com.foodymoody.be.comment_heart_count.infra.presistence.jpa;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCount;
import com.foodymoody.be.comment_heart_count.domain.CommentHeartCountId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentHeartCountJpaRepository extends JpaRepository<CommentHeartCount, CommentHeartCountId> {

    Optional<CommentHeartCount> findByCommentId(CommentId commentId);
}
