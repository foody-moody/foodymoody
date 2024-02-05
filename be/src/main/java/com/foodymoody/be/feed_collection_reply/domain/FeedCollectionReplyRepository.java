package com.foodymoody.be.feed_collection_reply.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedCollectionReplyRepository {

    FeedCollectionReply save(FeedCollectionReply reply);

    Optional<FeedCollectionReply> findById(FeedCollectionReplyId replyId);

    Slice<FeedCollectionReplaySummary> findByCommentId(
            FeedCollectionCommentId commentId, MemberId memberId, Pageable pageable
    );

    Slice<FeedCollectionReplaySummary> findByCommentId(
            FeedCollectionCommentId commentId, Pageable pageable
    );
}
