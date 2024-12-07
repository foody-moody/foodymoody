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

    private FeedCommentId feedCommentId;
    private Content feedCommentContent;
    private FeedId feedId;
    private String feedThumbnailUrl;

    public FeedCommentLikeNotificationDetails(
            FeedCommentId feedCommentId,
            Content feedCommentContent,
            FeedId feedId,
            String feedThumbnailUrl
    ) {
        this.feedCommentId = feedCommentId;
        this.feedCommentContent = feedCommentContent;
        this.feedId = feedId;
        this.feedThumbnailUrl = feedThumbnailUrl;
    }

}
