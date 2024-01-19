package com.foodymoody.be.feed_comment.domain.repository;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CommentRepository {

    FeedComment save(FeedComment feedComment);

    Optional<FeedComment> findById(FeedCommentId id);

    Slice<MemberCommentSummary> findWithMemberAllByFeedId(FeedId feedId, Pageable pageable);

    Slice<MemberCommentSummary> findWithMemberAllByFeedId(FeedId feedId, MemberId memberId, Pageable pageable);

}
