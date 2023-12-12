package com.foodymoody.be.feed_collection.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionJpaRepository extends JpaRepository<FeedCollection, FeedCollectionId> {

    @Query(
            "select _feedCollection.id as id " +
                    ", _feedCollection.title as title " +
                    ", _feedCollection.description as description " +
                    ", _feedCollection.authorId.value as authorId " +
                    ", _member.nickname as authorNickname " +
                    ", _image.url as authorProfileImageUrl " +
                    ", _taste_mood.name as authorMood " +
                    ", _feedCollection.heartCount as likeCount " +
                    ", _feedCollection.followerCount as followerCount " +
                    ", _feedCollection.thumbnailUrl as thumbnailUrl " +
                    ", _feedCollection.isPrivate as isPrivate " +
                    ", _feedCollection.createdAt as createdAt " +
                    ", _feedCollection.updatedAt as updatedAt " +
                    "FROM FeedCollection _feedCollection " +
                    "JOIN Member _member on _feedCollection.authorId = _member.id " +
                    "JOIN Image _image on _member.profileImage.imageId = _image.id " +
                    "JOIN TasteMood _taste_mood on _member.tasteMoodId = _taste_mood.id "
    )
    Slice<FeedCollectionSummary> findAllSummary(Pageable pageable);
}
