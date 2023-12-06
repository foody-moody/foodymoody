package com.foodymoody.be.comment_heart.infra.persistence;

import com.foodymoody.be.comment_heart.domain.CommentHeart;
import com.foodymoody.be.comment_heart.domain.CommentHeartRepository;
import com.foodymoody.be.comment_heart.infra.persistence.jpa.CommentHeartJpaRepository;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
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
    public void deleteByCommentIdAndMemberId(CommentId commentId, MemberId memberId) {
        commentHeartJpaRepository.deleteByCommentIdAndMemberId(commentId, memberId);
    }

    @Override
    public boolean existsByCommentIdAndMemberId(CommentId commentId, MemberId memberId) {
        return commentHeartJpaRepository.existsByCommentIdAndMemberId(commentId, memberId);
    }
}
