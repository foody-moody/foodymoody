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
public class FeedCommentReplyLikeNotificationDetails extends NotificationDetails {

    private FeedId feedId;
    private String feedThumbnailUrl;
    private FeedCommentId feedCommentId;
    private FeedReplyId feedReplyId;
    private Content feedReplyContent;

    public FeedCommentReplyLikeNotificationDetails(
            FeedId feedId,
            String feedThumbnailUrl,
            FeedCommentId feedCommentId,
            FeedReplyId feedReplyId,
            Content feedReplyContent
    ) {
        this.feedId = feedId;
        this.feedThumbnailUrl = feedThumbnailUrl;
        this.feedCommentId = feedCommentId;
        this.feedReplyId = feedReplyId;
        this.feedReplyContent = feedReplyContent;
    }

}
