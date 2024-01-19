package com.foodymoody.be.feed_collection_comment.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionComment;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentSummary;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionCommentJpaRepository extends
        JpaRepository<FeedCollectionComment, FeedCollectionCommentId> {

    @Query(
            "SELECT _comment.id AS id " +
                    ", _comment.feedCollectionId AS feedCollectionId " +
                    ", _comment.memberId AS memberId " +
                    ", _comment.content AS content " +
                    ", _comment.deleted AS deleted " +
                    ", (CASE WHEN EXISTS (select 1 from FeedCollectionReply _reply where _reply.commentId = _comment.id) " +
                    " THEN true " +
                    " ELSE false " +
                    " END) AS hasReply " +
                    ", _comment.createdAt AS createdAt " +
                    ", _comment.updatedAt AS updatedAt " +
                    ", _member.nickname AS nickname " +
                    ", _image.url AS profileImageUrl " +
                    ", _member.tasteMood.name AS mood " +
                    ", (_like.id IS NOT NULL) AS liked " +
                    ", _likeCount.count AS likeCount " +
                    "FROM FeedCollectionComment _comment " +
                    "JOIN Member _member ON _comment.memberId = _member.id " +
                    "JOIN Image _image ON _member.profileImage.id = _image.id " +
                    "JOIN FeedCollectionCommentLikeCount _likeCount on _likeCount.feedCollectionCommentId = _comment.id " +
                    "LEFT JOIN FeedCollectionCommentLike _like " +
                    "ON _comment.id = _like.commentId AND _like.memberId = :memberId " +
                    "WHERE _comment.id IN :commentIds "
    )
    Slice<FeedCollectionCommentSummary> findSummaryAllByIdIn(
            MemberId memberId,
            List<FeedCollectionCommentId> commentIds,
            Pageable pageable
    );

    @Query(
            "SELECT _comment.id AS id " +
                    ", _comment.feedCollectionId AS feedCollectionId " +
                    ", _comment.memberId AS memberId " +
                    ", _comment.content AS content " +
                    ", _comment.deleted AS deleted " +
                    ", (CASE WHEN EXISTS (select 1 from FeedCollectionReply _reply where _reply.commentId = _comment.id) " +
                    " THEN true " +
                    " ELSE false " +
                    " END) AS hasReply " +
                    ", _comment.createdAt AS createdAt " +
                    ", _comment.updatedAt AS updatedAt " +
                    ", _member.nickname AS nickname " +
                    ", _image.url AS profileImageUrl " +
                    ", _member.tasteMood.name AS mood " +
                    ", false AS liked " +
                    ", _likeCount.count AS likeCount " +
                    "FROM FeedCollectionComment _comment " +
                    "JOIN Member _member ON _comment.memberId = _member.id " +
                    "JOIN Image _image ON _member.profileImage.id = _image.id " +
                    "JOIN FeedCollectionCommentLikeCount _likeCount on _likeCount.feedCollectionCommentId = _comment.id " +
                    "WHERE _comment.id IN :commentIds "
    )
    Slice<FeedCollectionCommentSummary> findSummaryAllByIdIn(
            List<FeedCollectionCommentId> commentIds,
            Pageable pageable
    );
}
