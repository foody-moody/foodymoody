package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import java.util.Optional;

public interface FeedCollectionCommentRepository {

    FeedCollectionComment save(FeedCollectionComment feedCollectionComment);

    Optional<FeedCollectionComment> findById(FeedCollectionCommentId id);

    boolean existsById(FeedCollectionCommentId commentId);
}
