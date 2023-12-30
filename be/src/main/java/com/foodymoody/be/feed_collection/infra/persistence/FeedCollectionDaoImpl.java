package com.foodymoody.be.feed_collection.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionDao;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.feed_collection.domain.QFeedCollection;
import com.foodymoody.be.feed_collection.domain.QFeedCollectionMood;
import com.foodymoody.be.feed_collection.infra.persistence.jpa.FeedCollectionJpaRepository;
import com.foodymoody.be.feed_collection_like.domain.QFeedCollectionLike;
import com.foodymoody.be.feed_collection_like_count.domain.QFeedCollectionLikeCount;
import com.foodymoody.be.image.domain.QImage;
import com.foodymoody.be.member.domain.QMember;
import com.foodymoody.be.member.domain.QTasteMood;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionDaoImpl implements FeedCollectionDao {

    private final FeedCollectionJpaRepository repository;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<FeedCollectionSummary> findAllSummary(Pageable pageable) {
        QFeedCollection feedCollection = QFeedCollection.feedCollection;
        QMember member = QMember.member;
        QImage image = QImage.image;
        QTasteMood tasteMood = QTasteMood.tasteMood;
        QFeedCollectionLikeCount likeCount = QFeedCollectionLikeCount.feedCollectionLikeCount;
        QFeedCollectionMood mood = QFeedCollectionMood.feedCollectionMood;
        Expression<Boolean> liked = Expressions.constant(false);
        return getAllCollectionSummaries(pageable, feedCollection, member, image, tasteMood, likeCount, mood, liked);
    }

    @Override
    public List<FeedCollectionSummary> findAllSummary(MemberId memberId, Pageable pageable) {
        QFeedCollection feedCollection = QFeedCollection.feedCollection;
        QMember member = QMember.member;
        QImage image = QImage.image;
        QTasteMood tasteMood = QTasteMood.tasteMood;
        QFeedCollectionLikeCount likeCount = QFeedCollectionLikeCount.feedCollectionLikeCount;
        QFeedCollectionLike feedCollectionLike = QFeedCollectionLike.feedCollectionLike;
        Expression<Boolean> liked = JPAExpressions.selectOne().from(feedCollectionLike)
                .where(feedCollectionLike.memberId.eq(memberId)
                               .and(feedCollectionLike.feedCollectionId.eq(feedCollection.id)))
                .exists();
        QFeedCollectionMood mood = QFeedCollectionMood.feedCollectionMood;
        return getAllCollectionSummaries(pageable, feedCollection, member, image, tasteMood, likeCount, mood, liked);
    }

    @Override
    public Optional<FeedCollection> fetchById(FeedCollectionId feedCollectionId) {
        return repository.findById(feedCollectionId);
    }

    private List<FeedCollectionSummary> getAllCollectionSummaries(
            Pageable pageable, QFeedCollection feedCollection, QMember member, QImage image, QTasteMood tasteMood,
            QFeedCollectionLikeCount likeCount, QFeedCollectionMood mood, Expression<Boolean> liked
    ) {
        return queryFactory.from(feedCollection)
                .join(member).on(feedCollection.authorId.eq(member.id))
                .join(image).on(member.profileImage.id.eq(image.id))
                .join(tasteMood).on(member.tasteMood.eq(tasteMood))
                .join(likeCount).on(feedCollection.id.eq(likeCount.feedCollectionId))
                .join(mood).on(mood.in(feedCollection.moods.moodList))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(feedCollection.createdAt.desc())
                .transform(
                        GroupBy.groupBy(feedCollection.id)
                                .list(Projections.constructor(
                                        FeedCollectionSummary.class,
                                        feedCollection.id,
                                        feedCollection.thumbnailUrl,
                                        member.id,
                                        member.nickname,
                                        tasteMood.name,
                                        image.url,
                                        feedCollection.title,
                                        feedCollection.description,
                                        likeCount.count,
                                        feedCollection.followerCount,
                                        feedCollection.feedIds.ids.size(),
                                        feedCollection.commentIds.ids.size(),
                                        feedCollection.isPrivate,
                                        liked,
                                        GroupBy.list(mood.name),
                                        feedCollection.createdAt,
                                        feedCollection.updatedAt
                                ))
                );
    }
}
