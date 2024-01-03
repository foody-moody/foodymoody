package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedCollectionCommentRepository {

    FeedCollectionComment save(FeedCollectionComment feedCollectionComment);

    Optional<FeedCollectionComment> findById(FeedCollectionCommentId id);

    boolean existsById(FeedCollectionCommentId commentId);

    Slice<FeedCollectionCommentSummary> findSummaryAllByIdIn(
            MemberId memberId,
            List<FeedCollectionCommentId> commentIds,
            Pageable pageable
    );

    Slice<FeedCollectionCommentSummary> findSummaryAllByIdIn(
            List<FeedCollectionCommentId> commentIds,
            Pageable pageable
    );
}
