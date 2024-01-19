package com.foodymoody.be.feed_comment.application.dto.response;

import java.time.LocalDateTime;

public interface MemberReplySummary {

    String getReplyId();

    String getContent();

    String getMemberId();

    String getNickname();

    String getImageUrl();

    long getHeartCount();

    boolean isHearted();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
