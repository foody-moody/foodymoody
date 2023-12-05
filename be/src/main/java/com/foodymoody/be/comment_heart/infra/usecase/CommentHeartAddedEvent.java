package com.foodymoody.be.comment_heart.infra.usecase;

import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.event.NotificationType;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentHeartAddedEvent implements Event {

    private final String feedId;
    private final String content;
    private final NotificationType notificationType;
    private final CommentId commentId;
    private final String memberId;
    private final LocalDateTime createdAt;
}
