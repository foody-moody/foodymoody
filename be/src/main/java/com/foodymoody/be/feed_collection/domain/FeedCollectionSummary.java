package com.foodymoody.be.feed_collection.domain;

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

    String getCreatedAt();

    String getUpdatedAt();
}
