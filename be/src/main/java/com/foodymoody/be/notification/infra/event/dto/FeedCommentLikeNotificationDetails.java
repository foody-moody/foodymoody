package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedCommentLikeNotificationDetails extends NotificationDetails {

    private FeedCommentId commentId;
    private Content commentContent;
    private FeedId feedId;
    private String feedThumbnail;

    public FeedCommentLikeNotificationDetails(
            FeedCommentId commentId,
            Content commentContent,
            FeedId feedId,
            String feedThumbnail
    ) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.feedId = feedId;
        this.feedThumbnail = feedThumbnail;
    }
}
