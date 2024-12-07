package com.foodymoody.be.feed_collection.infra.persistence;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionDao;
import com.foodymoody.be.feed_collection.domain.FeedCollectionSummary;
import com.foodymoody.be.feed_collection.domain.QFeedCollection;
import com.foodymoody.be.feed_collection.infra.persistence.jpa.FeedCollectionJpaRepository;
import com.foodymoody.be.feed_collection_like.domain.QFeedCollectionLike;
import com.foodymoody.be.image.domain.QImage;
import com.foodymoody.be.member.domain.QMember;
import com.foodymoody.be.member.domain.QTasteMood;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class FeedCollectionDaoImpl implements FeedCollectionDao {

    private final FeedCollectionJpaRepository repository;
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<FeedCollectionSummary> findAllSummary(Pageable pageable) {
        Expression<Boolean> liked = Expressions.constant(false);
        return getSliceOfAllCollectionSummaries(pageable, liked);
    }

    @Override
    public Slice<FeedCollectionSummary> findAllSummary(MemberId memberId, Pageable pageable) {
        Expression<Boolean> liked = getLiked(memberId);
        return getSliceOfAllCollectionSummaries(pageable, liked);
    }

    @Override
    public Optional<FeedCollection> fetchById(FeedCollectionId feedCollectionId) {
        return repository.findByIdAndIsDeleted(feedCollectionId, false);
    }

    private Slice<FeedCollectionSummary> getSliceOfAllCollectionSummaries(
            Pageable pageable, Expression<Boolean> liked
    ) {
        // table
        QFeedCollection feedCollection = QFeedCollection.feedCollection;
        QMember member = QMember.member;
        QImage image = QImage.image;
        QTasteMood tasteMood = QTasteMood.tasteMood;

        // table join
        JPAQuery<?> query = queryFactory
                .from(feedCollection)
                .join(member).on(feedCollection.authorId.eq(member.id))
                .join(image).on(member.profileImage.id.eq(image.id))
                .join(tasteMood).on(member.tasteMood.eq(tasteMood))
                .where(feedCollection.isDeleted.isFalse())
                .where(feedCollection.isPrivate.isFalse())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1L);

        // sort
        for (Sort.Order order : pageable.getSort()) {
            SimplePath<Comparable<?>> compare = Expressions.path(
                    order.getProperty().getClass(), feedCollection, order.getProperty());
            OrderSpecifier<?> orderSpecifier = new OrderSpecifier<>(
                    order.isAscending() ? Order.ASC : Order.DESC, compare);
            query.orderBy(orderSpecifier);
        }

        // select
        List<FeedCollectionSummary> allCollectionSummaries = query.transform(
                GroupBy.groupBy(feedCollection.id)
                        .list(
                                Projections.constructor(
                                        FeedCollectionSummary.class,
                                        feedCollection.id,
                                        member.id,
                                        member.nickname,
                                        tasteMood.name,
                                        image.url,
                                        feedCollection.title,
                                        feedCollection.thumbnailUrl,
                                        feedCollection.description,
                                        feedCollection.likeCount,
                                        feedCollection.followerCount,
                                        feedCollection.feedIds.ids.size(),
                                        feedCollection.commentIds.ids.size(),
                                        liked,
                                        feedCollection.createdAt,
                                        feedCollection.updatedAt
                                )
                        )
        );
        boolean hasNext = allCollectionSummaries.size() > pageable.getPageSize();
        if (hasNext) {
            allCollectionSummaries.remove(allCollectionSummaries.size() - 1);
        }
        return new SliceImpl<>(allCollectionSummaries, pageable, hasNext);
    }

    private static Expression<Boolean> getLiked(MemberId memberId) {
        QFeedCollectionLike feedCollectionLike = QFeedCollectionLike.feedCollectionLike;
        return JPAExpressions
                .selectOne()
                .from(feedCollectionLike)
                .where(isLikedCollection(memberId))
                .exists();
    }

    private static BooleanExpression isLikedCollection(MemberId memberId) {
        QFeedCollection feedCollection = QFeedCollection.feedCollection;
        QFeedCollectionLike feedCollectionLike = QFeedCollectionLike.feedCollectionLike;
        return feedCollectionLike.memberId.eq(memberId)
                .and(feedCollectionLike.feedCollectionId.eq(feedCollection.id));
    }

}
