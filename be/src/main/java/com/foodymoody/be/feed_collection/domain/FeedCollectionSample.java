package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import groovy.transform.Immutable;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Immutable
@Subselect("select DISTINCT _feedCollection.id as id " +
        ", _feedCollection.author_id as author_id " +
        ", _member.nickname as author_nickname " +
        ", _taste_mood.name as author_mood " +
        ", _image.url as author_thumbnail_url " +
        ", _feedCollection.title as title " +
        ", _feedCollection.description as description " +
        ", _like_count.count as like_count " +
        ", _feedCollection.follower_count as follower_count " +
        ", _feedCollection.thumbnail_url as thumbnail_url " +
        ", _feedCollection.is_private as is_private " +
        ", (select count(id) from feed_collection_feed_ids where feed_collection_feed_ids.id = _feedCollection.id ) as feed_count " +
        ", (select count(id) from feed_collection_comment_ids where _feedCollectionCommentIds.comment_id = _feedCollection.id) as comment_count " +
        ", false as liked " +
        ", _feedCollection.moods_id as moods_id " +
        ", _feedCollection.created_at as created_at " +
        ", _feedCollection.updated_at as updated_at " +
        "FROM feed_collection _feedCollection " +
        "JOIN member _member on _feedCollection.author_id = _member.id " +
        "JOIN image _image on _member.profile_image_id = _image.id " +
        "JOIN taste_mood _taste_mood on _member.taste_mood_id = _taste_mood.id " +
        "LEFT JOIN feed_collection_like_count _like_count on _feedCollection.id = _like_count.feed_collection_id " +
        "LEFT JOIN feed_collection_feed_ids as _feedCollecitonIds on _feedCollection.id = _feedCollecitonIds.feed_id " +
        "LEFT JOIN feed_collection_moods as _feedCollectionMoods on _feedCollection.id = _feedCollectionMoods.id " +
        "LEFT JOIN feed_collection_moods_mood_list as _feedCollectionMoodsMoodList on _feedCollectionMoods.id = _feedCollectionMoodsMoodList.feed_collection_moods_id " +
        "LEFT JOIN feed_collection_mood as _feedCollectionMood on _feedCollectionMoodsMoodList.mood_list_id = _feedCollectionMood.id " +
        "LEFT JOIN feed_collection_comment_ids as _feedCollectionCommentIds on _feedCollection.id = _feedCollectionCommentIds.comment_id "
)
@Table(name = "feed_collection")
public class FeedCollectionSample {

    @Id
    private FeedCollectionId id;
    @AttributeOverride(name = "value", column = @Column(name = "author_id"))
    private MemberId authorId;
    private String authorNickname;
    private String authorMood;
    private String authorThumbnailUrl;
    private String title;
    private String description;
    private int likeCount;
    private int followerCount;
    private String thumbnailUrl;
    private boolean isPrivate;
    private int feedCount;
    private int commentCount;
    @OneToOne(fetch = FetchType.EAGER)
    private FeedCollectionMoods moods;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
