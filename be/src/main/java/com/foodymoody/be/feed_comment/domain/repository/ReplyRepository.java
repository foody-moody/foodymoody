package com.foodymoody.be.feed_comment.domain.repository;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReplyRepository {

    Slice<MemberReplySummary> findByCommentId(FeedCommentId feedCommentId, Pageable pageable);

    Slice<MemberReplySummary> findByCommentIdAndMemberId(
            FeedCommentId feedCommentId, MemberId memberId, Pageable pageable
    );

    boolean existsById(FeedReplyId feedReplyId);

    Optional<FeedReply> findById(FeedReplyId feedReplyId);
}
