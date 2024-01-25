package com.foodymoody.be.feed_comment.domain.entity;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;

public interface MemberFeedReplySummary {

    FeedReplyId getReplyId();

    Content getContent();

    MemberId getMemberId();

    String getNickname();

    String getImageUrl();

    long getLikeCount();

    boolean isLiked();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
