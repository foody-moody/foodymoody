package com.foodymoody.be.feed_comment.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedCommentSummary;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedCommentJpaRepository extends JpaRepository<FeedComment, FeedCommentId> {

    @Query("select _comment.id as id " +
            ",_comment.content as content " +
            ",_member.id as memberId" +
            ",_member.nickname as nickname" +
            ",_image.url as imageUrl" +
            ",_comment.hasReply as hasReply " +
            ",size(_reply.commentList) as replyCount" +
            ",_comment.createdAt as createdAt" +
            ",_comment.updatedAt as updatedAt " +
            ",_likeCount.count as likeCount" +
            ",false as liked " +
            "from FeedComment _comment " +
            "left join _comment.feedReplyComments _reply " +
            "left join Member _member on _member.id = _comment.memberId " +
            "left join Image _image on _image.id = _member.profileImage.id " +
            "left join FeedCommentLikeCount _likeCount on _likeCount.feedCommentId = _comment.id " +
            "where _comment.feedId = :feedId and _comment.deleted = false")
    Slice<MemberFeedCommentSummary> findWithMemberAllByFeedId(
            @Param("feedId") FeedId feedId,
            @Param("pageable") Pageable pageable
    );

    @Query("select _comment.id as id" +
            ",_comment.content as content" +
            ",_member.id as memberId" +
            ",_member.nickname as nickname" +
            ",_image.url as imageUrl" +
            ",_comment.hasReply as hasReply" +
            ",size(_reply.commentList) as replyCount" +
            ",_comment.createdAt as createdAt" +
            ",_comment.updatedAt as updatedAt, " +
            "_likeCount.count as likeCount" +
            ",(case when _like is not null then true else false end) as liked " +
            "from FeedComment _comment " +
            "left join _comment.feedReplyComments _reply " +
            "left join Member _member on _member.id = _comment.memberId " +
            "left join Image _image on _image.id = _member.profileImage.id " +
            "left join FeedCommentLike _like on _like.feedCommentId = _comment.id and _like.memberId = :memberId " +
            "left join FeedCommentLikeCount _likeCount on _likeCount.feedCommentId = _comment.id " +
            "where _comment.feedId = :feedId and _comment.deleted = false")
    Slice<MemberFeedCommentSummary> findWithMemberAllByFeedIdAndMemberId(
            FeedId feedId,
            MemberId memberId,
            Pageable pageable
    );

    @Query("SELECT COUNT(_feedComment) FROM FeedComment _feedComment WHERE _feedComment.feedId = :feedId and _feedComment.deleted = false")
    Optional<Long> fetchCountByFeedId(@Param("feedId") FeedId feedId);

}
