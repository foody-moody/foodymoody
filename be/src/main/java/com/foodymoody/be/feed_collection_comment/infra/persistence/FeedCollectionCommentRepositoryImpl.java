package com.foodymoody.be.feed_collection_comment.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentRepository;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentSummary;
import com.foodymoody.be.feed_collection_comment.infra.persistence.jpa.FeedCollectionCommentJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionCommentRepositoryImpl implements FeedCollectionCommentRepository {

    private final FeedCollectionCommentJpaRepository repository;

    @Override
    public FeedCollectionComment save(FeedCollectionComment feedCollectionComment) {
        return repository.save(feedCollectionComment);
    }

    @Override
    public Optional<FeedCollectionComment> findById(FeedCollectionCommentId id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(FeedCollectionCommentId commentId) {
        return repository.existsById(commentId);
    }

    @Override
    public Slice<FeedCollectionCommentSummary> findSummaryAllByIdIn(
            MemberId memberId,
            List<FeedCollectionCommentId> commentIds,
            Pageable pageable
    ) {
        return repository.findSummaryAllByIdIn(memberId, commentIds, pageable);
    }

    @Override
    public Slice<FeedCollectionCommentSummary> findSummaryAllByIdIn(
            List<FeedCollectionCommentId> commentIds,
            Pageable pageable
    ) {
        return repository.findSummaryAllByIdIn(commentIds, pageable);
    }

}
