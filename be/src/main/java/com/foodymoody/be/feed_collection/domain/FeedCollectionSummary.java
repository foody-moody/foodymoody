package com.foodymoody.be.feed_collection.domain;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedCollectionSummary {

    String getId();

    String getTitle();

    String getDescription();

    String getAuthorId();

    String getAuthorNickname();

    String getAuthorProfileImageUrl();

    String getAuthorMood();

    long getLikeCount();

    long getCommentCount();

    long getFeedCount();

    List<String> getMoods();

    String getThumbnailUrl();

    boolean isLiked();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
