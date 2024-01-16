package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentRepliedAddedEvent implements Event {

    private CommentId commentId;
    private ReplyId replyId;
    private MemberId toMemberId;
    private MemberId fromMemberId;
    private FeedId feedId;
    private Content content;
    private LocalDateTime createdAt;

    private CommentRepliedAddedEvent(CommentId id, ReplyId replyId, MemberId toMemberId, MemberId fromMemberId,
                                     FeedId feedId, Content content,
            LocalDateTime createdAt) {
        this.replyId = replyId;
        this.commentId = id;
        this.toMemberId = toMemberId;
        this.fromMemberId = fromMemberId;
        this.feedId = feedId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentRepliedAddedEvent of(CommentId id, ReplyId replyId, MemberId toMemberId, MemberId fromMemberId,
                                              Content content, FeedId feedId, LocalDateTime createdAt
    ) {
        return new CommentRepliedAddedEvent(id, replyId, toMemberId, fromMemberId, feedId, content, createdAt);
    }
}
