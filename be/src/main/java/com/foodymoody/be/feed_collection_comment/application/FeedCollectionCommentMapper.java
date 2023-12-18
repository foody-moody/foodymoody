package com.foodymoody.be.feed_collection_comment.application;


import com.foodymoody.be.common.util.CommentContent;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class FeedCollectionCommentMapper {

    public FeedCollectionComment toEntity(
            FeedCollectionId feedCollectionId, CommentContent content, MemberId memberId,
            FeedCollectionCommentId feedCollectionCommentId, LocalDateTime now
    ) {
        return new FeedCollectionComment(
                feedCollectionCommentId, feedCollectionId, memberId, content, now);
    }
}
