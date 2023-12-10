package com.foodymoody.be.comment.infra.persistence.jpa;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyJpaRepository extends JpaRepository<Reply, ReplyId> {

    @Query("select _reply.id.value as replyId, _reply.content as content,"
            + "_reply.memberId.value as memberId, _member.nickname as nickname, "
            + "_image.url as imageUrl, _reply.createdAt as createdAt, _reply.updatedAt as updatedAt, "
            + "_heartCount.count as heartCount "
            + "from Comment _comment "
            + "left join _comment.replyComments.commentList _reply "
            + "left join Member _member on _reply.memberId = _member.id.value "
            + "left join Image _image on _member.profileImage = _image.id "
            + "left join CommentHeartCount _heartCount on _heartCount.commentId = _comment.id "
            + "where _comment.id = :commentId")
    Slice<MemberReplySummary> findReplyByCommentId(CommentId commentId, Pageable pageable);

    @Query("select _reply.id.value as replyId, _reply.content as content,"
            + "_reply.memberId.value as memberId, _member.nickname as nickname, "
            + "_image.url as imageUrl, _reply.createdAt as createdAt, _reply.updatedAt as updatedAt, "
            + "_heartCount.count as heartCount,(case when _heart is not null then true else false end) as hearted "
            + "from Comment _comment "
            + "left join _comment.replyComments.commentList _reply "
            + "left join Member _member on _reply.memberId = _member.id "
            + "left join Image _image on _member.profileImage = _image.id "
            + "left join CommentHeartCount _heartCount on _heartCount.commentId = _comment.id "
            + "left join CommentHeart _heart on _heart.commentId = _comment.id and _heart.memberId = :memberId "
            + "where _comment.id = :commentId")
    Slice<MemberReplySummary> findReplyByCommentIdAndMemberId(CommentId commentId, MemberId memberId,
            Pageable pageable);
}
