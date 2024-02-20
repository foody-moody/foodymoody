package com.foodymoody.be.feed_comment.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedReplySummary;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedReplyJpaRepository extends JpaRepository<FeedReply, FeedReplyId> {

    @Query("select _reply.id as replyId" +
            ", _reply.content as content" +
            ", _reply.memberId as memberId" +
            ", _member.nickname as nickname" +
            ", COALESCE(_image.url, null ) as imageUrl" +
            ", _reply.createdAt as createdAt" +
            ", _reply.updatedAt as updatedAt" +
            ", _likeCount.count as likeCount" +
            ",false as liked " +
            "from FeedComment _comment " +
            "left join _comment.feedReplyComments.commentList _reply on _reply.deleted = false " +
            "left join Member _member on _reply.memberId = _member.id.value " +
            "left join Image _image on _member.profileImage.id = _image.id AND _image.deleted = false " +
            "left join FeedCommentLikeCount _likeCount on _likeCount.feedCommentId = _comment.id " +
            "where _comment.id = :feedCommentId AND _reply.deleted = false")
    Slice<MemberFeedReplySummary> findReplyByCommentId(FeedCommentId feedCommentId, Pageable pageable);

    @Query("select _reply.id as replyId, _reply.content as content" +
            ", _reply.memberId as memberId" +
            ", _member.nickname as nickname" +
            ", COALESCE(_image.url, null ) as imageUrl" +
            ", _reply.createdAt as createdAt" +
            ", _reply.updatedAt as updatedAt" +
            ", _replyLikeCount.count as likeCount" +
            ", (case when _feedRelyLike is not null then true else false end) as liked " +
            "from FeedComment _comment " +
            "left join _comment.feedReplyComments.commentList _reply on _reply.deleted = false " +
            "left join Member _member on _reply.memberId = _member.id " +
            "left join Image _image on _member.profileImage.id = _image.id AND _image.deleted = false " +
            "left join FeedReplyLikeCount _replyLikeCount on _replyLikeCount.feedReplyId = _reply.id " +
            "left join FeedReplyLike _feedRelyLike on _feedRelyLike.feedReplyId = _reply.id and _feedRelyLike.memberId = :memberId " +
            "where _comment.id = :feedCommentId AND _reply.deleted = false")
    Slice<MemberFeedReplySummary> findReplyByCommentIdAndMemberId(
            FeedCommentId feedCommentId,
            MemberId memberId,
            Pageable pageable
    );

    Optional<FeedReply> findByIdAndDeleted(FeedReplyId feedReplyId, boolean deleted);
}
