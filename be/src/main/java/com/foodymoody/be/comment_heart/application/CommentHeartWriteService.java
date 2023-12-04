package com.foodymoody.be.comment_heart.application;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment_heart.domain.CommentHeart;
import com.foodymoody.be.comment_heart.domain.CommentHeartId;
import com.foodymoody.be.comment_heart.domain.CommentHeartIdFactory;
import com.foodymoody.be.comment_heart.domain.CommentHeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentHeartWriteService {

    private final CommentHeartRepository commentHeartRepository;

    @Transactional
    public void registerCommentHeart(CommentId commentId, String memberId) {
        CommentHeartId commentHeartId = CommentHeartIdFactory.newId();
        CommentHeart commentHeart = new CommentHeart(commentHeartId, commentId, memberId);
        commentHeartRepository.save(commentHeart);
    }

    @Transactional
    public void deleteCommentHeart(CommentId commentId, String memberId) {
        commentHeartRepository.deleteByCommentIdAndMemberId(commentId, memberId);
    }

    public boolean existsByCommentIdAndMemberId(CommentId commentId, String memberId) {
        return commentHeartRepository.existsByCommentIdAndMemberId(commentId, memberId);
    }
}
