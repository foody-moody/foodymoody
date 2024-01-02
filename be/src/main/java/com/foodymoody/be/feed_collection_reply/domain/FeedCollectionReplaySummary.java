package com.foodymoody.be.feed_collection_reply.domain;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;

public interface FeedCollectionReplaySummary {

    FeedCollectionReplyId getId();

    Content getContent();

    LocalDateTime getUpdatedAt();

    LocalDateTime getCreatedAt();

    MemberId getMemberId();

    String getNickname();

    String getProfileUrl();

    boolean isLiked();

    long getLikeCount();
}
