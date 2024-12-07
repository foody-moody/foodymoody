package com.foodymoody.be.notification.infra.event.dto;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.notification.domain.NotificationDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedCommentReplyNotificationDetails extends NotificationDetails {

    private FeedCommentId feedCommentId;
    private FeedId feedId;
    private String feedThumbnailUrl;
    private FeedReplyId feedReplyId;
    private Content feedReplyContent;

    public FeedCommentReplyNotificationDetails(
            FeedCommentId feedCommentId,
            FeedId feedId,
            String feedThumbnailUrl,
            FeedReplyId feedReplyId,
            Content feedReplyContent
    ) {
        this.feedCommentId = feedCommentId;
        this.feedId = feedId;
        this.feedThumbnailUrl = feedThumbnailUrl;
        this.feedReplyId = feedReplyId;
        this.feedReplyContent = feedReplyContent;
    }

}
