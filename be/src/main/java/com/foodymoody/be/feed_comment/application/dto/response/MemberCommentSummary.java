package com.foodymoody.be.feed_comment.application.dto.response;

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

    long getReplyCount();

    long getHeartCount();

    boolean isHearted();
}
