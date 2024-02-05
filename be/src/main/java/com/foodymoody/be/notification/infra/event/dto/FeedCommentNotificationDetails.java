package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedCommentNotificationDetails extends NotificationDetails {

    private FeedId feedId;
    private String feedThumbnail;
    private FeedCommentId commentId;
    private Content commentContent;

    public FeedCommentNotificationDetails(
            FeedId feedId,
            String feedThumbnail,
            FeedCommentId commentId,
            Content commentContent
    ) {
        this.feedId = feedId;
        this.feedThumbnail = feedThumbnail;
        this.commentId = commentId;
        this.commentContent = commentContent;
    }
}
