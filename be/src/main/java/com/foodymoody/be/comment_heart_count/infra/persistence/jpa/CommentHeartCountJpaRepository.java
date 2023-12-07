package com.foodymoody.be.comment_heart_count.infra.persistence.jpa;

import com.foodymoody.be.comment_heart_count.domain.CommentHeartCount;
import com.foodymoody.be.common.util.ids.CommentHeartCountId;
import com.foodymoody.be.common.util.ids.CommentId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentHeartCountJpaRepository extends JpaRepository<CommentHeartCount, CommentHeartCountId> {

    Optional<CommentHeartCount> findByCommentId(CommentId commentId);

    @Modifying
    @Query("update CommentHeartCount c set c.count = c.count + 1 where c.commentId = :commentId")
    void incrementCount(CommentId commentId);

    @Modifying
    @Query("update CommentHeartCount c set c.count = c.count - 1 where c.commentId = :commentId")
    void decrementCount(CommentId commentId);
}
