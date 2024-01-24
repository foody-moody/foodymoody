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
    private String feedCollectionThumbnailUrl;
    private FeedCollectionCommentId feedCollectionCommentId;
    private Content feedCollectionCommentContent;

    public FeedCollectionCommentNotificationDetails(
            FeedCollectionId feedCollectionId,
            String feedCollectionThumbnailUrl,
            FeedCollectionCommentId feedCollectionCommentId,
            Content feedCollectionCommentContent
    ) {
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionThumbnailUrl = feedCollectionThumbnailUrl;
        this.feedCollectionCommentId = feedCollectionCommentId;
        this.feedCollectionCommentContent = feedCollectionCommentContent;
    }
}
