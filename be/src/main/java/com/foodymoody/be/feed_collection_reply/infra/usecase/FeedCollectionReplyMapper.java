package com.foodymoody.be.feed_collection_reply.infra.usecase;

import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplaySummary;
import org.springframework.stereotype.Component;

@Component
public class FeedCollectionReplyMapper {

    public FeedCollectionReplyResponse toResponse(FeedCollectionReplaySummary summary) {
        return new FeedCollectionReplyResponse(
                summary.getId(),
                summary.getContent(),
                summary.getCreatedAt(),
                summary.getUpdatedAt(),
                summary.getMemberId(),
                summary.getNickname(),
                summary.getProfileUrl()
        );
    }
}
