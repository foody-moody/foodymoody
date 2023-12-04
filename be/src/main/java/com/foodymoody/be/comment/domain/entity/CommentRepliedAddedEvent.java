package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.Event;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentRepliedAddedEvent implements Event {

    private CommentId commentId;
    private ReplyId replyId;
    private String toMemberId;
    private String fromMemberId;
    private String content;
    private LocalDateTime createdAt;

    private CommentRepliedAddedEvent(CommentId id, ReplyId replyId, String toMemberId, String fromMemberId,
            String content,
            LocalDateTime createdAt) {
        this.replyId = replyId;
        this.commentId = id;
        this.toMemberId = toMemberId;
        this.fromMemberId = fromMemberId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentRepliedAddedEvent of(CommentId id, ReplyId replyId, String toMemberId, String fromMemberId,
            String content,
            LocalDateTime createdAt) {
        return new CommentRepliedAddedEvent(id, replyId, toMemberId, fromMemberId, content, createdAt);
    }
}
