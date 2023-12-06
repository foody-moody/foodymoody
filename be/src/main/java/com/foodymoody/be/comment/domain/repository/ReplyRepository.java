package com.foodymoody.be.comment.domain.repository;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.comment.domain.entity.ReplyId;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReplyRepository {

    Slice<MemberReplySummary> findByCommentId(CommentId commentId, Pageable pageable);

    Slice<MemberReplySummary> findByCommentIdAndMemberId(CommentId commentId, MemberId memberId, Pageable pageable);

    boolean existsById(ReplyId replyId);

    Optional<Reply> findById(ReplyId replyId);
}
