package com.foodymoody.be.feed_collection_reply.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.application.FeedCollectionReplyReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyReadUseCase {

    private final FeedCollectionReplyReadService readService;
    private final FeedCollectionReplyMapper mapper;

    public Slice<FeedCollectionReplyResponse> fetch(
            FeedCollectionCommentId commentId, MemberId memberId, Pageable pageable
    ) {
        var summaries = readService.fetch(commentId, memberId, pageable);
        return summaries.map(mapper::toResponse);
    }

    public Slice<FeedCollectionReplyResponse> fetch(FeedCollectionCommentId commentId, Pageable pageable) {
        var summaries = readService.fetch(commentId, pageable);
        return summaries.map(mapper::toResponse);
    }
}
