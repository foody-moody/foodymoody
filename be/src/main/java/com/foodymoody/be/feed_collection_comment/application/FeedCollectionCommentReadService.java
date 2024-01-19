package com.foodymoody.be.feed_collection_comment.application;

import com.foodymoody.be.common.exception.FeedCollectionCommentNotFoundException;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentRepository;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentSummary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentReadService {

    private final FeedCollectionCommentRepository repository;

    public void validateExistence(FeedCollectionCommentId commentId) {
        if (repository.existsById(commentId)) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
    }

    public Slice<FeedCollectionCommentSummary> findSummaryAllByIdIn(
            MemberId memberId,
            List<FeedCollectionCommentId> commentIds,
            Pageable pageable
    ) {
        return repository.findSummaryAllByIdIn(memberId, commentIds, pageable);
    }

    public Slice<FeedCollectionCommentSummary> findSummaryAllByIdIn(
            List<FeedCollectionCommentId> commentIds,
            Pageable pageable
    ) {
        return repository.findSummaryAllByIdIn(commentIds, pageable);
    }

    public FeedCollectionComment findById(FeedCollectionCommentId toFeedCollectionCommentId) {
        return repository.findById(toFeedCollectionCommentId)
                .orElseThrow(FeedCollectionCommentNotFoundException::new);
    }
}
