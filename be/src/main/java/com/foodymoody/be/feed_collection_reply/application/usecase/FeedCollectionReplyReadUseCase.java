package com.foodymoody.be.feed_collection_reply.application.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.application.service.FeedCollectionReplyReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyReadUseCase {

    private final FeedCollectionReplyReadService readService;

    public Slice<FeedCollectionReplyResponse> fetch(
            FeedCollectionCommentId commentId, MemberId memberId, Pageable pageable
    ) {
        var summaries = readService.fetch(commentId, memberId, pageable);
        return summaries.map(FeedCollectionReplyMapper::toResponse);
    }

    public Slice<FeedCollectionReplyResponse> fetch(FeedCollectionCommentId commentId, Pageable pageable) {
        var summaries = readService.fetch(commentId, pageable);
        return summaries.map(FeedCollectionReplyMapper::toResponse);
    }

}
