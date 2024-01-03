package com.foodymoody.be.comment.infra.persistence;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.comment.domain.repository.ReplyRepository;
import com.foodymoody.be.comment.infra.persistence.jpa.ReplyJpaRepository;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import java.util.Optional;
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
    public Slice<MemberReplySummary> findByCommentIdAndMemberId(CommentId commentId, MemberId memberId,
            Pageable pageable) {
        return replyJpaRepository.findReplyByCommentIdAndMemberId(commentId, memberId, pageable);
    }

    @Override
    public boolean existsById(ReplyId replyId) {
        return replyJpaRepository.existsById(replyId);
    }

    @Override
    public Optional<Reply> findById(ReplyId replyId) {
        return replyJpaRepository.findById(replyId);
    }
}
