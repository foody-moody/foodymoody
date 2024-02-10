package com.foodymoody.be.member.infra.persistence.jpa;

import static com.foodymoody.be.feed.domain.entity.QFeed.feed;
import static com.foodymoody.be.feed_collection.domain.QFeedCollection.feedCollection;
import static com.foodymoody.be.feed_collection.domain.QFeedCollectionMood.feedCollectionMood;
import static com.foodymoody.be.feed_collection_like.domain.QFeedCollectionLike.feedCollectionLike;
import static com.foodymoody.be.image.domain.QImage.image;
import static com.foodymoody.be.member.domain.QFollow.follow;
import static com.foodymoody.be.member.domain.QMember.member;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection_like.domain.QFeedCollectionLike;
import com.foodymoody.be.member.application.dto.MyFeedCollectionWithFeedIdsSummary;
import com.foodymoody.be.member.application.dto.response.FeedCollectionMoodResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileImageResponse;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionAuthorResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionMoodSummary;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import com.foodymoody.be.member.application.dto.response.TasteMoodResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<MemberProfileResponse> fetchMemberProfileResponseById(MemberId id, MemberId currentMemberId) {

        MemberProfileResponse queryResult = jpaQueryFactory
                .select(
                        Projections.constructor(
                                MemberProfileResponse.class,
                                member.id,
                                Projections.constructor(
                                        MemberProfileImageResponse.class,
                                        member.profileImage.id,
                                        member.profileImage.url
                                ),
                                member.nickname,
                                member.email,
                                Projections.constructor(
                                        TasteMoodResponse.class,
                                        member.tasteMood.id,
                                        member.tasteMood.name
                                ),
                                member.myFollowings.follows.size(),
                                member.myFollowers.follows.size(),
                                isFollowing(currentMemberId),
                                isFollowed(currentMemberId),
                                feed.count()
                        )
                )
                .from(member)
                .leftJoin(feed).on(feed.memberId.eq(member.id))
                .where(member.id.eq(id))
                .fetchOne();

        if (Objects.nonNull(queryResult.getId())) {
            return Optional.of(queryResult);
        }
        return Optional.empty();
    }

    @Override
    public MyFeedCollectionsResponse fetchMyCollectionResponse(MemberId id, MemberId currentMemberId,
            Pageable pageable) {
        // 컬렉션 작성자, 컬렉션 전체 개수 조회
        Tuple findById = jpaQueryFactory
                .select(
                        member.id,
                        image.url,
                        feedCollection.count()
                )
                .from(member)
                .leftJoin(image).on(image.id.eq(member.profileImage.id))
                .leftJoin(feedCollection).on(feedCollection.authorId.eq(member.id))
                .where(member.id.eq(id))
                .fetchOne();

        MyFeedCollectionAuthorResponse authorSummary = new MyFeedCollectionAuthorResponse(
                findById.get(0, MemberId.class),
                findById.get(1, String.class));

        // 컬렉션 목록 슬라이스 조회
        QFeedCollectionLike liked = new QFeedCollectionLike("liked");

        List<MyFeedCollectionResponse> collectionSummariesQueryResult = jpaQueryFactory
                .select(
                        Projections.constructor(
                                MyFeedCollectionResponse.class,
                                feedCollection.id,
                                feedCollection.title,
                                feedCollection.thumbnailUrl,
                                feedCollection.feedIds.ids.size(),
                                feedCollectionLike.count(),
                                feedCollection.commentIds.ids.size(),
                                feedCollection.createdAt,
                                feedCollection.updatedAt,
                                liked.count().gt(0)
                        ))
                .from(feedCollection)
                .leftJoin(feedCollectionLike).on(feedCollectionLike.feedCollectionId.eq(feedCollection.id))
                .leftJoin(liked)
                .on(feedCollectionLike.feedCollectionId.eq(feedCollection.id).and(eqMemberIdIfNotNull(currentMemberId)))
                .innerJoin(feedCollectionMood).on(feedCollection.moods.moodList.contains(feedCollectionMood))
                .leftJoin(feedCollection.feedIds.ids)
                .leftJoin(feedCollection.commentIds.ids)
                .where(feedCollection.authorId.eq(id).and(feedCollection.isDeleted.isFalse()))
                .groupBy(feedCollection.id)
                .orderBy(feedCollection.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        // 컬렉션 목록 슬라이스의 무드 조회
        List<MyFeedCollectionMoodSummary> collectionMoodSummariesQueryResult = jpaQueryFactory
                .select(
                        Projections.constructor(
                                MyFeedCollectionMoodSummary.class,
                                feedCollectionMood.id,
                                feedCollectionMood.name,
                                feedCollection.id
                        ))
                .from(feedCollection)
                .innerJoin(feedCollectionMood).on(feedCollection.moods.moodList.contains(feedCollectionMood))
                .orderBy(feedCollection.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        collectionSummariesQueryResult.forEach(
                queryResult -> queryResult.setMoods(
                        collectionMoodSummariesQueryResult.stream()
                                .filter(moodQueryResult -> moodQueryResult.getFeedCollectionId()
                                        .equals(queryResult.getId()))
                                .map(moodSummary -> new FeedCollectionMoodResponse(moodSummary.getId(),
                                        moodSummary.getName()))
                                .collect(Collectors.toUnmodifiableList())
                )
        );

        boolean hasNext = false;
        if (collectionSummariesQueryResult.size() > pageable.getPageSize()) {
            collectionSummariesQueryResult.remove(pageable.getPageSize());
            hasNext = true;
        }
        SliceImpl<MyFeedCollectionResponse> collectionSummariesSlice = new SliceImpl<>(collectionSummariesQueryResult,
                pageable, hasNext);

        return new MyFeedCollectionsResponse(
                findById.get(2, Long.class),
                authorSummary,
                collectionSummariesSlice
        );
    }

    @Override
    public List<MyFeedCollectionWithFeedIdsSummary> fetchMyFeedCollectionWithFeedIds(MemberId currentMemberId) {
        List<FeedCollection> myFeedCollections =
                (List<FeedCollection>) jpaQueryFactory
                        .from(feedCollection)
                        .where(feedCollection.authorId.eq(currentMemberId))
                        .orderBy(feedCollection.createdAt.desc())
                        .fetch();

        return myFeedCollections.stream()
                .map(
                        fc -> new MyFeedCollectionWithFeedIdsSummary(
                                fc.getId(),
                                fc.getTitle(),
                                fc.getFeedIds()
                        )
                ).collect(Collectors.toUnmodifiableList());
    }

    private Expression<Boolean> isFollowed(MemberId currentMemberId) {
        if (Objects.nonNull(currentMemberId)) {
            return ExpressionUtils.as(
                    JPAExpressions.select(follow.count().gt(0))
                            .from(follow)
                            .where(follow.follower.eq(member)
                                    .and(follow.followed.id.eq(currentMemberId))),
                    "followed"
            );
        }
        return Expressions.constant(false);
    }

    private Expression<Boolean> isFollowing(MemberId currentMemberId) {
        if (Objects.nonNull(currentMemberId)) {
            return ExpressionUtils.as(
                    JPAExpressions.select(follow.count().gt(0))
                            .from(follow)
                            .where(follow.followed.eq(member)
                                    .and(follow.follower.id.eq(currentMemberId))),
                    "following"
            );
        }
        return Expressions.constant(false);
    }

    private BooleanExpression eqMemberIdIfNotNull(MemberId currentMemberId) {
        if (Objects.nonNull(currentMemberId)) {
            return feedCollectionLike.memberId.eq(currentMemberId);
        }
        return Expressions.asBoolean(true).isFalse();
    }
}
