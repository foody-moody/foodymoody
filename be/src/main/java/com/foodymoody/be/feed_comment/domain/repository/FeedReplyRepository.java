package com.foodymoody.be.feed_comment.domain.repository;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedReplySummary;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedReplyRepository {

    Slice<MemberFeedReplySummary> findByCommentId(FeedCommentId feedCommentId, Pageable pageable);

    Slice<MemberFeedReplySummary> findByCommentIdAndMemberId(
            FeedCommentId feedCommentId, MemberId memberId, Pageable pageable
    );

    boolean existsById(FeedReplyId feedReplyId);

    Optional<FeedReply> findById(FeedReplyId feedReplyId);
}
