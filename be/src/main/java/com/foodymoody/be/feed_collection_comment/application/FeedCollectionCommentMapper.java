package com.foodymoody.be.feed_collection_comment.application;


import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import java.time.LocalDateTime;

public class FeedCollectionCommentMapper {

    private FeedCollectionCommentMapper() {
        throw new AssertionError();
    }

    public static FeedCollectionComment toEntity(
            FeedCollectionId feedCollectionId,
            Content content,
            MemberId memberId,
            FeedCollectionCommentId feedCollectionCommentId,
            LocalDateTime createdAt
    ) {
        return new FeedCollectionComment(
                feedCollectionCommentId,
                feedCollectionId,
                memberId,
                content,
                createdAt
        );
    }
}
