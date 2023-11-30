package com.foodymoody.be.comment.application.dto.response;

import java.time.LocalDateTime;

public interface MemberCommentSummary {


    String getId();

    String getContent();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    String getMemberId();

    String getNickname();

    String getImageUrl();

    boolean isHasReply();

    int getReplyCount();
}
