package com.foodymoody.be.comment_heart.application;

import com.foodymoody.be.comment_heart.domain.CommentHeart;
import com.foodymoody.be.comment_heart.domain.CommentHeartIdFactory;
import com.foodymoody.be.comment_heart.domain.CommentHeartRepository;
import com.foodymoody.be.common.util.ids.CommentHeartId;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentHeartWriteService {

    private final CommentHeartRepository commentHeartRepository;

    @Transactional
    public CommentHeart registerCommentHeart(CommentId commentId, MemberId memberId) {
        CommentHeartId commentHeartId = CommentHeartIdFactory.newId();
        LocalDateTime now = LocalDateTime.now();
        CommentHeart commentHeart = new CommentHeart(commentHeartId, commentId, memberId, now, now);
        return commentHeartRepository.save(commentHeart);
    }

    @Transactional
    public void deleteCommentHeart(CommentId commentId, MemberId memberId) {
        commentHeartRepository.deleteByCommentIdAndMemberId(commentId, memberId);
    }

    public boolean existsByCommentIdAndMemberId(CommentId commentId, MemberId memberId) {
        return commentHeartRepository.existsByCommentIdAndMemberId(commentId, memberId);
    }
}
