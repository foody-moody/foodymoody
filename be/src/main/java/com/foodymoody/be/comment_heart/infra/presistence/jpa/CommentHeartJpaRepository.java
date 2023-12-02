package com.foodymoody.be.comment_heart.infra.presistence.jpa;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart.domain.CommentHeart;
import com.foodymoody.be.comment_heart.domain.CommentHeartId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentHeartJpaRepository extends JpaRepository<CommentHeart, CommentHeartId> {

    void deleteByCommentIdAndMemberId(CommentId commentId, String memberId);
}
