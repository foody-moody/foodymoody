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
    private String feedThumbnailUrl;
    private FeedCommentId feedCommentId;
    private Content feedCommentContent;

    public FeedCommentNotificationDetails(
            FeedId feedId,
            String feedThumbnailUrl,
            FeedCommentId feedCommentId,
            Content feedCommentContent
    ) {
        this.feedId = feedId;
        this.feedThumbnailUrl = feedThumbnailUrl;
        this.feedCommentId = feedCommentId;
        this.feedCommentContent = feedCommentContent;
    }
}
