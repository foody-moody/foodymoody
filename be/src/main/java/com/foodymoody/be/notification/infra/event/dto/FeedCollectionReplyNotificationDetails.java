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
    private String feedCollectionThumbnailUrl;
    private FeedCollectionCommentId feedCollectionCommentId;
    private FeedCollectionReplyId feedCollectionReplyId;
    private Content feedCollectionReplyContent;

    public FeedCollectionReplyNotificationDetails(
            FeedCollectionId feedCollectionId,
            String feedCollectionThumbnailUrl,
            FeedCollectionCommentId feedCollectionCommentId,
            FeedCollectionReplyId feedCollectionReplyId,
            Content feedCollectionReplyContent
    ) {
        this.feedCollectionId = feedCollectionId;
        this.feedCollectionThumbnailUrl = feedCollectionThumbnailUrl;
        this.feedCollectionCommentId = feedCollectionCommentId;
        this.feedCollectionReplyId = feedCollectionReplyId;
        this.feedCollectionReplyContent = feedCollectionReplyContent;
    }

}
