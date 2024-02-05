package com.foodymoody.be.member.infra.persistence.jpa;

import static com.foodymoody.be.feed_collection.domain.QFeedCollection.*;
import static com.foodymoody.be.feed_collection.domain.QFeedCollectionMood.*;
import static com.foodymoody.be.feed_collection_like.domain.QFeedCollectionLike.feedCollectionLike;
import static com.foodymoody.be.image.domain.QImage.*;
import static com.foodymoody.be.member.domain.QMember.*;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.domain.QFeedCollectionLike;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionsResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionAuthorResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionMoodResponse;
import com.foodymoody.be.member.application.dto.response.MyFeedCollectionResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.SliceImpl;

@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MyFeedCollectionsResponse fetchMyCollectionResponse(MemberId id, MemberId currentMemberId, Pageable pageable) {
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
                                feedCollection.feedIds.ids.size(),
                                feedCollectionLike.count(),
                                feedCollection.commentIds.ids.size(),
                                feedCollection.createdAt,
                                feedCollection.updatedAt,
                                liked.count().gt(0)
                                ))
                .from(feedCollection)
                .leftJoin(feedCollectionLike).on(feedCollectionLike.feedCollectionId.eq(feedCollection.id))
                .leftJoin(liked).on(feedCollectionLike.feedCollectionId.eq(feedCollection.id).and(feedCollectionLike.memberId.eq(currentMemberId)))
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
        List<MyFeedCollectionMoodResponse> collectionMoodSummariesQueryResult = jpaQueryFactory
                .select(
                        Projections.constructor(
                                MyFeedCollectionMoodResponse.class,
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
                        collectionMoodSummariesQueryResult.stream().filter(
                                moodQueryResult -> moodQueryResult.getFeedCollectionId().equals(queryResult.getId())
                        ).collect(Collectors.toUnmodifiableList())
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
}
