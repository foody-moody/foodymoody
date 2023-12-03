package com.foodymoody.be.comment.infra.persistence;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.entity.ReplyId;
import com.foodymoody.be.comment.domain.repository.ReplyRepository;
import com.foodymoody.be.comment.infra.persistence.jpa.ReplyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyRepositoryImpl implements ReplyRepository {

    private final ReplyJpaRepository replyJpaRepository;

    @Override
    public Slice<MemberReplySummary> findByCommentId(CommentId commentId, Pageable pageable) {
        return replyJpaRepository.findReplyByCommentId(commentId, pageable);
    }

    @Override
    public boolean existsById(ReplyId replyId) {
        return replyJpaRepository.existsById(replyId);
    }
}
