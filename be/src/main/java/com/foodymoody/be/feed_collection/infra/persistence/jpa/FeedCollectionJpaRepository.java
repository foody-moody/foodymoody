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
                    "FROM FeedCollection _feedCollection " +
                    "JOIN Member _member on _feedCollection.authorId = _member.id " +
                    "JOIN Image _image on _member.profileImage.id = _image.id " +
                    "JOIN TasteMood _taste_mood on _member.tasteMood = _taste_mood " +
                    "JOIN FeedCollectionLikeCount _like_count on _feedCollection.id = _like_count.feedCollectionId "
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
                    ", (case when _like is not null then true else false end) as liked " +
                    "FROM FeedCollection _feedCollection " +
                    "JOIN Member _member on _feedCollection.authorId = _member.id " +
                    "JOIN Image _image on _member.profileImage.id = _image.id " +
                    "JOIN TasteMood _taste_mood on _member.tasteMood = _taste_mood " +
                    "LEFT JOIN FeedCollectionLike _like on _feedCollection.id = _like.feedCollectionId and _like.memberId = :memberId " +
                    "JOIN FeedCollectionLikeCount _like_count on _feedCollection.id = _like_count.feedCollectionId "
    )
    Slice<FeedCollectionSummary> findAllSummary(MemberId memberId, Pageable pageable);
}
