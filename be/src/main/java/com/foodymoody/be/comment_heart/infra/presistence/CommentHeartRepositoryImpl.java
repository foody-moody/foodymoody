package com.foodymoody.be.comment_heart.infra.presistence;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart.domain.CommentHeart;
import com.foodymoody.be.comment_heart.domain.CommentHeartRepository;
import com.foodymoody.be.comment_heart.infra.presistence.jpa.CommentHeartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CommentHeartRepositoryImpl implements CommentHeartRepository {

    private final CommentHeartJpaRepository commentHeartJpaRepository;

    @Override
    public CommentHeart save(CommentHeart commentHeart) {
        return commentHeartJpaRepository.save(commentHeart);
    }

    @Override
    public void deleteByCommentIdAndMemberId(CommentId commentId, String memberId) {
        commentHeartJpaRepository.deleteByCommentIdAndMemberId(commentId, memberId);
    }
}
