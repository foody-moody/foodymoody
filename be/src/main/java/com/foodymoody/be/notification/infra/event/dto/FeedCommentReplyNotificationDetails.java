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

    private FeedCommentId commentId;
    private Content commentContent;
    private FeedId feedId;
    private String feedThumbnail;
    private FeedReplyId replyId;
    private Content replyContent;

    public FeedCommentReplyNotificationDetails(
            FeedCommentId commentId,
            Content commentContent,
            FeedId feedId,
            String feedThumbnail,
            FeedReplyId replyId,
            Content replyContent
    ) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.feedId = feedId;
        this.feedThumbnail = feedThumbnail;
        this.replyId = replyId;
        this.replyContent = replyContent;
    }
}
