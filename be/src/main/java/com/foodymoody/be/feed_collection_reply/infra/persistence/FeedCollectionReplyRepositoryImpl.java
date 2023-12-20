package com.foodymoody.be.feed_collection_reply.infra.persistence;

import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReply;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyRepository;
import com.foodymoody.be.feed_collection_reply.infra.persistence.jpa.FeedCollectionReplyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionReplyRepositoryImpl implements FeedCollectionReplyRepository {

    private final FeedCollectionReplyJpaRepository repository;

    @Override
    public FeedCollectionReply save(FeedCollectionReply reply) {
        return repository.save(reply);
    }
}
