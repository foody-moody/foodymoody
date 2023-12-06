package com.foodymoody.be.comment_heart.infra.usecase;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.event.NotificationType;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentHeartAddedEvent implements Event {

    private final FeedId feedId;
    private final String content;
    private final NotificationType notificationType;
    private final CommentId commentId;
    private final MemberId memberId;
    private final LocalDateTime createdAt;
}
