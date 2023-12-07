package com.foodymoody.be.reply_heart.infra.usecase;

import com.foodymoody.be.comment.domain.entity.ReplyId;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyHeartAddedEvent {

    private final FeedId feedId;
    private final String content;
    private final NotificationType notificationType;
    private final CommentId commentId;
    private final ReplyId replyId;
    private final MemberId memberId;
    private final LocalDateTime createdAt;
}
