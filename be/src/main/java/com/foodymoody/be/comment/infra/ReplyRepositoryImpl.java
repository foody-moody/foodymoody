package com.foodymoody.be.comment.infra;

import com.foodymoody.be.comment.application.dto.response.ReplyResponse;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.repository.ReplyRepository;
import com.foodymoody.be.comment.infra.presistence.ReplyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReplyRepositoryImpl implements ReplyRepository {

    private final ReplyJpaRepository replyJpaRepository;

    @Override
    public Slice<ReplyResponse> findReplyWithMemberAllById(CommentId commentId, Pageable pageable) {
        return replyJpaRepository.findReplyWithMemberAllById(commentId, pageable);
    }
}
