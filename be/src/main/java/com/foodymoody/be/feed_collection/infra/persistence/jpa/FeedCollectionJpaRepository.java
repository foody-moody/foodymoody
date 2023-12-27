package com.foodymoody.be.feed_collection.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionJpaRepository extends JpaRepository<FeedCollection, FeedCollectionId> {

    @Query(
            "select _feedCollection.id.value as id " +
                    ", _feedCollection.title as title " +
                    ", _feedCollection.description as description " +
                    ", _feedCollection.authorId.value as authorId " +
                    ", _member.nickname as authorNickname " +
                    ", _image.url as authorProfileImageUrl " +
                    ", _taste_mood.name as authorMood " +
                    ", _like_count.count as likeCount " +
                    ", _feedCollection.followerCount as followerCount " +
                    ", _feedCollection.thumbnailUrl as thumbnailUrl " +
                    ", _feedCollection.isPrivate as isPrivate " +
                    ", _feedCollection.createdAt as createdAt " +
                    ", _feedCollection.updatedAt as updatedAt " +
                    ", count(_feed) as feedCount " +
                    ", count(_comment) as commentCount " +
                    ", false as liked " +
                    "FROM FeedCollection _feedCollection " +
                    "JOIN Member _member on _feedCollection.authorId = _member.id " +
                    "JOIN Image _image on _member.profileImage.id = _image.id " +
                    "JOIN TasteMood _taste_mood on _member.tasteMood = _taste_mood " +
                    "JOIN FeedCollectionLikeCount _like_count on _feedCollection.id = _like_count.feedCollectionId " +
                    "JOIN _feedCollection.feedIds.ids as _feed " +
                    "JOIN _feedCollection.commentIds.ids as _comment " +
                    "JOIN _feedCollection.moods.moodList as _mood " +
                    "GROUP BY _feedCollection.id"
    )
    Slice<FeedCollectionSummary> findAllSummary(Pageable pageable);

    @Query(
            "select _feedCollection.id.value as id " +
                    ", _feedCollection.title as title " +
                    ", _feedCollection.description as description " +
                    ", _feedCollection.authorId.value as authorId " +
                    ", _member.nickname as authorNickname " +
                    ", _image.url as authorProfileImageUrl " +
                    ", _taste_mood.name as authorMood " +
                    ", _like_count.count as likeCount " +
                    ", _feedCollection.followerCount as followerCount " +
                    ", _feedCollection.thumbnailUrl as thumbnailUrl " +
                    ", _feedCollection.isPrivate as isPrivate " +
                    ", _feedCollection.createdAt as createdAt " +
                    ", _feedCollection.updatedAt as updatedAt " +
                    ", _feed.size as feedCount " +
                    ", _comment.size as commentCount " +
                    ", (case when _like is not null then true else false end) as liked " +
                    ", _moods.name as moods " +
                    "FROM FeedCollection _feedCollection " +
                    "JOIN Member _member on _feedCollection.authorId = _member.id " +
                    "JOIN Image _image on _member.profileImage.id = _image.id " +
                    "JOIN TasteMood _taste_mood on _member.tasteMood = _taste_mood " +
                    "LEFT JOIN FeedCollectionLike _like on _feedCollection.id = _like.feedCollectionId and _like.memberId = :memberId " +
                    "LEFT JOIN FeedCollectionLikeCount _like_count on _feedCollection.id = _like_count.feedCollectionId " +
                    "LEFT JOIN _feedCollection.feedIds.ids as _feed " +
                    "LEFT JOIN _feedCollection.commentIds.ids as _comment " +
                    "LEFT JOIN _feedCollection.moods.moodList as _moods "
    )
    Slice<FeedCollectionSummary> findAllSummary(MemberId memberId, Pageable pageable);
}
