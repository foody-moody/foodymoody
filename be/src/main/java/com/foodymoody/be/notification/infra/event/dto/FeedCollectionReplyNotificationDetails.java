package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedCollectionReplyNotificationDetails extends NotificationDetails {

    private FeedCollectionId feedCollectionId;
    private String feedCollectionTitle;
    private String feedCollectionThumbnailUrl;
    private FeedCollectionCommentId commentId;
    private Content commentContent;
    private FeedCollectionReplyId replyId;
    private Content replyContent;

    public FeedCollectionReplyNotificationDetails(
            FeedCollectionId feedCollectionId,
            String feedCollectionTitle,
            String feedCollectionThumbnailUrl,
            FeedCollectionCommentId commentId,
            Content commentContent,
            FeedCollectionReplyId replyId,
            Content replyContent
    ) {
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionTitle = feedCollectionTitle;
        this.feedCollectionThumbnailUrl = feedCollectionThumbnailUrl;
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.replyId = replyId;
        this.replyContent = replyContent;
    }
}
