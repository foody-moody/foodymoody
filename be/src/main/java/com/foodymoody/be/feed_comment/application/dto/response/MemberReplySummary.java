package com.foodymoody.be.feed_comment.application.dto.response;

import com.foodymoody.be.common.util.Content;
import java.time.LocalDateTime;

public interface MemberReplySummary {

    String getReplyId();

    Content getContent();

    String getMemberId();

    String getNickname();

    String getImageUrl();

    long getHeartCount();

    boolean isHearted();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
