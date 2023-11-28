package com.foodymoody.be.comment.domain.repository;

import com.foodymoody.be.comment.application.dto.response.ReplyResponse;
import com.foodymoody.be.comment.domain.entity.CommentId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReplyRepository {

    Slice<ReplyResponse> findReplyWithMemberAllById(CommentId commentId, Pageable pageable);
}
