package com.foodymoody.be.comment.domain.repository;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CommentRepository {

    Comment save(Comment comment);

    Optional<Comment> findById(CommentId id);

    Slice<MemberCommentSummary> findWithMemberAllByFeedId(FeedId feedId, Pageable pageable);

    Slice<MemberCommentSummary> findWithMemberAllByFeedId(FeedId feedId, MemberId memberId, Pageable pageable);

}
