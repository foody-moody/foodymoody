package com.foodymoody.be.feed_collection_reply.application.service;

import com.foodymoody.be.common.exception.FeedCollectionReplyNotFoundException;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReply;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyRepository;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplySummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyReadService {

    private final FeedCollectionReplyRepository repository;

    public Slice<FeedCollectionReplySummary> fetch(
            FeedCollectionCommentId commentId,
            MemberId memberId,
            Pageable pageable
    ) {
        return repository.findByCommentId(commentId, memberId, pageable);
    }

    public Slice<FeedCollectionReplySummary> fetch(FeedCollectionCommentId commentId, Pageable pageable) {
        return repository.findByCommentId(commentId, pageable);
    }

    public FeedCollectionReply fetchById(FeedCollectionReplyId replyId) {
        return repository.findById(replyId)
                .orElseThrow(FeedCollectionReplyNotFoundException::new);
    }
}
