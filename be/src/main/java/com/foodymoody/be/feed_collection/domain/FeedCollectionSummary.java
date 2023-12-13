package com.foodymoody.be.feed_collection.domain;

import java.time.LocalDateTime;

public interface FeedCollectionSummary {

    String getId();

    String getTitle();

    String getDescription();

    String getAuthorId();

    String getAuthorNickname();

    String getAuthorProfileImageUrl();

    String getAuthorMood();

    long getLikeCount();

    String getThumbnailUrl();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();
}
