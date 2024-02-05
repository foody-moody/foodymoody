package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedCollectionCommentLikeNotificationDetails extends NotificationDetails {

    private FeedCollectionCommentId feedCollectionCommentId;
    private Content feedCollectionCommentContent;
    private FeedCollectionId feedCollectionId;
    private String feedCollectionTitle;

    public FeedCollectionCommentLikeNotificationDetails(
            FeedCollectionCommentId feedCollectionCommentId, Content feedCollectionCommentContent,
            FeedCollectionId feedCollectionId, String feedCollectionTitle
    ) {
        this.feedCollectionCommentId = feedCollectionCommentId;
        this.feedCollectionCommentContent = feedCollectionCommentContent;
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionTitle = feedCollectionTitle;
    }
}
