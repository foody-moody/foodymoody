package com.foodymoody.be.feed_collection_reply.application.usecase;

import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplySummary;

public class FeedCollectionReplyMapper {

    private FeedCollectionReplyMapper() {
        throw new AssertionError();
    }

    public static FeedCollectionReplyResponse toResponse(FeedCollectionReplySummary summary) {
        return new FeedCollectionReplyResponse(
                summary.getId(),
                summary.getContent(),
                summary.getCreatedAt(),
                summary.getUpdatedAt(),
                summary.getMemberId(),
                summary.getNickname(),
                summary.getProfileUrl(),
                summary.isLiked(),
                summary.getLikeCount()
        );
    }
}
