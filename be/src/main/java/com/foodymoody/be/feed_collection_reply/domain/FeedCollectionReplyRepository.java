package com.foodymoody.be.feed_collection_reply.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import java.util.Optional;

public interface FeedCollectionReplyRepository {

    FeedCollectionReply save(FeedCollectionReply reply);

    Optional<FeedCollectionReply> findById(FeedCollectionReplyId replyId);
}
