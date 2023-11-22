package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.dto.ReplyResponse;
import com.foodymoody.be.comment.domain.CommentId;
import com.foodymoody.be.comment.repository.ReplyRepository;
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
