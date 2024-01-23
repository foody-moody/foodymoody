package com.foodymoody.be.feed_comment.application.dto.response;

import com.foodymoody.be.common.util.Content;
import java.time.LocalDateTime;

public interface MemberCommentSummary {


    String getId();

    Content getContent();

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
