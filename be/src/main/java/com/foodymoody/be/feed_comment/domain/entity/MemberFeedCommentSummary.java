package com.foodymoody.be.feed_comment.domain.entity;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;

public interface MemberFeedCommentSummary {

    FeedCommentId getId();

    Content getContent();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    MemberId getMemberId();

    String getNickname();

    String getImageUrl();

    boolean isHasReply();

    long getReplyCount();

    long getLikeCount();

    boolean isLiked();

}
