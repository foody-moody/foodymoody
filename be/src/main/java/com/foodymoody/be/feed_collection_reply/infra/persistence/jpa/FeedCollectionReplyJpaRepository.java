package com.foodymoody.be.feed_collection_reply.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCollectionReplyJpaRepository extends JpaRepository<FeedCollectionReply, FeedCollectionReplyId> {

}
