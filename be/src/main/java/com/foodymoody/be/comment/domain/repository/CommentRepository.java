package com.foodymoody.be.comment.domain.repository;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.common.util.ids.CommentId;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(CommentId id);

    Slice<MemberCommentSummary> findWithMemberAllByFeedId(String feedId, Pageable pageable);

    boolean existsById(CommentId commentId);

    Slice<MemberCommentSummary> findWithMemberAllByFeedId(String feedId, String memberId, Pageable pageable);
}
