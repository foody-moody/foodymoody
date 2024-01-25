package com.foodymoody.be.feed_comment.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.entity.MemberFeedReplySummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedReplyJpaRepository extends JpaRepository<FeedReply, FeedReplyId> {

    @Query("select _reply.id as replyId" +
            ", _reply.content as content" +
            ", _reply.memberId as memberId" +
            ", _member.nickname as nickname" +
            ", _image.url as imageUrl" +
            ", _reply.createdAt as createdAt" +
            ", _reply.updatedAt as updatedAt" +
            ", _likeCount.count as likeCount" +
            ",false as liked " +
            "from FeedComment _comment " +
            "left join _comment.feedReplyComments.commentList _reply " +
            "left join Member _member on _reply.memberId = _member.id.value " +
            "left join Image _image on _member.profileImage.id = _image.id " +
            "left join FeedCommentLikeCount _likeCount on _likeCount.feedCommentId = _comment.id " +
            "where _comment.id = :feedCommentId")
    Slice<MemberFeedReplySummary> findReplyByCommentId(FeedCommentId feedCommentId, Pageable pageable);

    @Query("select _reply.id as replyId, _reply.content as content" +
            ", _reply.memberId as memberId" +
            ", _member.nickname as nickname" +
            ", _image.url as imageUrl" +
            ", _reply.createdAt as createdAt" +
            ", _reply.updatedAt as updatedAt" +
            ", _replyLikeCount.count as likeCount" +
            ", (case when _feedRelyLike is not null then true else false end) as liked " +
            "from FeedComment _comment " +
            "left join _comment.feedReplyComments.commentList _reply " +
            "left join Member _member on _reply.memberId = _member.id " +
            "left join Image _image on _member.profileImage.id = _image.id " +
            "left join ReplyHeartCount _replyLikeCount on _replyLikeCount.feedReplyId = _reply.id " +
            "left join FeedReplyLike _feedRelyLike on _feedRelyLike.feedReplyId = _reply.id and _feedRelyLike.memberId = :memberId " +
            "where _comment.id = :feedCommentId")
    Slice<MemberFeedReplySummary> findReplyByCommentIdAndMemberId(
            FeedCommentId feedCommentId,
            MemberId memberId,
            Pageable pageable
    );
}
