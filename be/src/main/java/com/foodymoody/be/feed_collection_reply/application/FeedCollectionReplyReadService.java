package com.foodymoody.be.feed_collection_reply.application;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplaySummary;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyReadService {

    private final FeedCollectionReplyRepository repository;

    public Slice<FeedCollectionReplaySummary> fetch(
            FeedCollectionCommentId commentId, MemberId memberId, Pageable pageable
    ) {
        return repository.findByCommentId(commentId, memberId, pageable);
    }

    public Slice<FeedCollectionReplaySummary> fetch(FeedCollectionCommentId commentId, Pageable pageable) {
        return repository.findByCommentId(commentId, pageable);
    }
}