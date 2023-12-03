package com.foodymoody.be.comment.domain.repository;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.entity.ReplyId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReplyRepository {

    Slice<MemberReplySummary> findByCommentId(CommentId commentId, Pageable pageable);

    boolean existsById(ReplyId replyId);
}
