package com.foodymoody.be.feed_collection_reply.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReply;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyRepository;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplySummary;
import com.foodymoody.be.feed_collection_reply.infra.persistence.jpa.FeedCollectionReplyJpaRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionReplyRepositoryImpl implements FeedCollectionReplyRepository {

    private final FeedCollectionReplyJpaRepository repository;

    @Override
    public FeedCollectionReply save(FeedCollectionReply reply) {
        return repository.save(reply);
    }

    @Override
    public Optional<FeedCollectionReply> findById(FeedCollectionReplyId replyId) {
        return repository.findById(replyId);
    }

    @Override
    public Slice<FeedCollectionReplySummary> findByCommentId(
            FeedCollectionCommentId commentId,
            MemberId memberId,
            Pageable pageable
    ) {
        return repository.findSummaryByCommentId(commentId, memberId, pageable);
    }

    @Override
    public Slice<FeedCollectionReplySummary> findByCommentId(FeedCollectionCommentId commentId, Pageable pageable) {
        return repository.findSummaryByCommentId(commentId, pageable);
    }
}
