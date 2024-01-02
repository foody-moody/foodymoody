package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;

public interface FeedCollectionCommentSummary {

    FeedCollectionCommentId getId();

    FeedCollectionId getFeedId();

    Content getContent();

    boolean isDeleted();

    boolean isHasReply();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    int getReplyCount();

    boolean isLiked();

    int getLikeCount();

    MemberId getMemberId();

    String getNickname();

    String getProfileImageUrl();

    String getMood();
}
