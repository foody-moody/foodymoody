package com.foodymoody.be.feed_collection_comment.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionCommentJpaRepository extends
        JpaRepository<FeedCollectionComment, FeedCollectionCommentId> {

}
