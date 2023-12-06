package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentRepliedAddedEvent implements Event {

    private CommentId commentId;
    private ReplyId replyId;
    private String toMemberId;
    private String fromMemberId;
    private FeedId feedId;
    private String content;
    private LocalDateTime createdAt;

    private CommentRepliedAddedEvent(CommentId id, ReplyId replyId, String toMemberId, String fromMemberId,
            FeedId feedId, String content,
            LocalDateTime createdAt) {
        this.replyId = replyId;
        this.commentId = id;
        this.toMemberId = toMemberId;
        this.fromMemberId = fromMemberId;
        this.feedId = feedId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentRepliedAddedEvent of(CommentId id, ReplyId replyId, String toMemberId, String fromMemberId,
            String content, FeedId feedId, LocalDateTime createdAt) {
        return new CommentRepliedAddedEvent(id, replyId, toMemberId, fromMemberId, feedId, content, createdAt);
    }
}
