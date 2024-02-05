package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedCollectionCommentNotificationDetails extends NotificationDetails {

    private FeedCollectionId feedCollectionId;
    private String feedCollectionTitle;
    private String feedCollectionThumbnail;
    private FeedCollectionCommentId commentId;
    private Content commentContent;

    public FeedCollectionCommentNotificationDetails(
            FeedCollectionId feedCollectionId,
            String feedCollectionTitle,
            String feedCollectionThumbnail,
            FeedCollectionCommentId commentId,
            Content commentContent
    ) {
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionTitle = feedCollectionTitle;
        this.feedCollectionThumbnail = feedCollectionThumbnail;
        this.commentId = commentId;
        this.commentContent = commentContent;
    }
}
