package com.foodymoody.be.comment.application.dto.response;

import java.time.LocalDateTime;

public interface MemberReplySummary {

    String getReplyId();

    String getContent();

    String getMemberId();

    String getNickname();

    String getImageUrl();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
