package com.foodymoody.be.comment.domain.entity;

import com.foodymoody.be.common.event.NotificationType;
import java.time.LocalDateTime;

public class CommentDomainMapper {

    private CommentDomainMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CommentAddNotificationEvent mapperToNotificationEvent(String feedId) {
        return CommentAddNotificationEvent.of("1",
                feedId + "에 댓글이 추가되였습니다.",
                NotificationType.COMMENT_ADDED,
                LocalDateTime.now());
    }

}
