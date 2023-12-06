package com.foodymoody.be.comment_heart.infra.persistence.jpa;

import com.foodymoody.be.comment_heart.domain.CommentHeart;
import com.foodymoody.be.common.util.ids.CommentHeartId;
import com.foodymoody.be.common.util.ids.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentHeartJpaRepository extends JpaRepository<CommentHeart, CommentHeartId> {

    void deleteByCommentIdAndMemberId(CommentId commentId, String memberId);

    boolean existsByCommentIdAndMemberId(CommentId commentId, String memberId);
}
