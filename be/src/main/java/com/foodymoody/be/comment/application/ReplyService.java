package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.response.ReplyResponse;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    public Slice<ReplyResponse> fetchAllReply(String id, Pageable pageable) {
        CommentId commentId = new CommentId(id);
        return replyRepository.findReplyWithMemberAllById(commentId, pageable);
    }
}
