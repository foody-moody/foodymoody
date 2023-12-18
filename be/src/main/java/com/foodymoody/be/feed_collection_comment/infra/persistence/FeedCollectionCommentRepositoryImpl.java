package com.foodymoody.be.feed_collection_comment.infra.persistence;

import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentRepository;
import com.foodymoody.be.feed_collection_comment.infra.persistence.jpa.FeedCollectionCommentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionCommentRepositoryImpl implements FeedCollectionCommentRepository {

    private final FeedCollectionCommentJpaRepository repository;

    @Override
    public FeedCollectionComment save(FeedCollectionComment feedCollectionComment) {
        return repository.save(feedCollectionComment);
    }
}
