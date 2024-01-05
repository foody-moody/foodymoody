package com.foodymoody.be.feed_collection_reply.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplaySummary;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionReplyJpaRepository extends JpaRepository<FeedCollectionReply, FeedCollectionReplyId> {

    @Query(
            "SELECT _reply.id as id" +
                    ",_reply.content as content" +
                    ",_reply.updatedAt as updatedAt" +
                    ",_reply.createdAt as createdAt" +
                    ",_member.id as memberId" +
                    ",_member.nickname as nickname" +
                    ",_image.url as profileUrl " +
                    ", _like_count.count as likeCount " +
                    ", false as liked " +
                    "FROM FeedCollectionReply _reply " +
                    "JOIN Member _member ON _reply.memberId = _member.id " +
                    "JOIN Image _image ON _member.profileImage = _image.id " +
                    "LEFT JOIN FeedCollectionReplyLikeCount _like_count ON _like_count.feedCollectionReplyId = _reply.id " +
                    "WHERE _reply.commentId = :commentId "
    )
    Slice<FeedCollectionReplaySummary> findSummaryByCommentId(FeedCollectionCommentId commentId, Pageable pageable);

    @Query(
            "SELECT _reply.id as id" +
                    ",_reply.content as content" +
                    ",_reply.updatedAt as updatedAt" +
                    ",_reply.createdAt as createdAt" +
                    ",_member.id as memberId" +
                    ",_member.nickname as nickname" +
                    ",_image.url as profileUrl " +
                    ", _like_count.count as likeCount " +
                    ", (_like.id IS NOT NULL ) as liked " +
                    "FROM FeedCollectionReply _reply " +
                    "JOIN Member _member ON _reply.memberId = _member.id " +
                    "JOIN Image _image ON _member.profileImage = _image.id " +
                    "LEFT JOIN FeedCollectionReplyLikeCount _like_count ON _like_count.feedCollectionReplyId = _reply.id " +
                    "LEFT JOIN FeedCollectionReplyLike _like ON _like.feedCollectionReplyId = _reply.id AND _like.memberId = :memberId " +
                    "WHERE _reply.commentId = :commentId "
    )
    Slice<FeedCollectionReplaySummary> findSummaryByCommentId(
            FeedCollectionCommentId commentId, MemberId memberId, Pageable pageable
    );
}