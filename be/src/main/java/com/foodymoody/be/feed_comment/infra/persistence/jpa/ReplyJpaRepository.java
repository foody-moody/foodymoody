package com.foodymoody.be.feed_comment.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyJpaRepository extends JpaRepository<FeedReply, FeedReplyId> {

    @Query("select _reply.id.value as replyId" +
            ", _reply.content as content" +
            ", _reply.memberId.value as memberId" +
            ", _member.nickname as nickname" +
            ", _image.url as imageUrl" +
            ", _reply.createdAt as createdAt" +
            ", _reply.updatedAt as updatedAt" +
            ", coalesce(_likeCount.count,0) as heartCount,false as hearted "
            + "from FeedComment _comment "
            + "left join _comment.feedReplyComments.commentList _reply "
            + "left join Member _member on _reply.memberId = _member.id.value "
            + "left join Image _image on _member.profileImage.id = _image.id "
            + "left join FeedCommentLikeCount _likeCount on _likeCount.feedCommentId = _comment.id "
            + "where _comment.id = :feedCommentId")
    Slice<MemberReplySummary> findReplyByCommentId(FeedCommentId feedCommentId, Pageable pageable);

    @Query("select _reply.id.value as replyId, _reply.content as content" +
            ", _reply.memberId.value as memberId" +
            ", _member.nickname as nickname" +
            ", _image.url as imageUrl" +
            ", _reply.createdAt as createdAt" +
            ", _reply.updatedAt as updatedAt" +
            ", coalesce(_replyLikeCount.count,0) as heartCount" +
            ", (case when _feedRelyLike is not null then true else false end) as hearted "
            + "from FeedComment _comment "
            + "left join _comment.feedReplyComments.commentList _reply "
            + "left join Member _member on _reply.memberId = _member.id "
            + "left join Image _image on _member.profileImage.id = _image.id "
            + "left join ReplyHeartCount _replyLikeCount on _replyLikeCount.feedReplyId = _reply.id "
            +
            "left join FeedReplyLike _feedRelyLike on _feedRelyLike.feedReplyId = _reply.id and _feedRelyLike.memberId = :memberId "
            + "where _comment.id = :feedCommentId")
    Slice<MemberReplySummary> findReplyByCommentIdAndMemberId(
            FeedCommentId feedCommentId, MemberId memberId,
            Pageable pageable
    );
}
