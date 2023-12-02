package com.foodymoody.be.comment.infra.presistence.jpa;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.entity.CommentId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentJpaRepository extends JpaRepository<Comment, CommentId> {

    @Query("select _comment.id.value as id,_comment.content as content,"
            + "_member.id.id as memberId,_member.nickname as nickname,_image.url as imageUrl,_comment.hasReply as hasReply,"
            + "_reply.commentList.size as replyCount,_comment.createdAt as createdAt,_comment.updatedAt as updatedAt, "
            + "_heartCount.count as heartCount "
            + "from Comment _comment "
            + "left join _comment.replyComments _reply "
            + "left join Member _member on _member.id.id = _comment.memberId "
            + "left join Image _image on _image.id = _member.profileImageId "
            + "left join CommentHeartCount _heartCount on _heartCount.commentId = _comment.id "
            + "where _comment.feedId = :feedId and _comment.deleted = false")
    Slice<MemberCommentSummary> findWithMemberAllByFeedId(
            @Param("feedId") String feedId,
            @Param("pageable") Pageable pageable
    );

    @Query("select _reply.id.value as replyId, _reply.content as content,"
            + "_reply.memberId as memberId, _member.nickname as nickname, "
            + "_image.url as imageUrl, _reply.createdAt as createdAt, _reply.updatedAt as updatedAt "
            + "from Comment _comment "
            + "join _comment.replyComments.commentList _reply "
            + "join Member _member "
            + "on _reply.memberId = _member.id.id "
            + "join Image _image "
            + "on _member.profileImageId = _image.id "
            + "where _comment.id = :commentId")
    Slice<MemberReplySummary> findReplyByCommentId(CommentId commentId, Pageable pageable);

    @Query("select _comment.id.value as id,_comment.content as content,"
            + "_member.id.id as memberId,_member.nickname as nickname,_image.url as imageUrl,_comment.hasReply as hasReply,"
            + "_reply.commentList.size as replyCount,_comment.createdAt as createdAt,_comment.updatedAt as updatedAt, "
            + "_heartCount.count as heartCount, "
            + "(case when _heart is not null then true else false end) as hearted "
            + "from Comment _comment "
            + "left join _comment.replyComments _reply "
            + "left join Member _member on _member.id.id = _comment.memberId "
            + "left join Image _image on _image.id = _member.profileImageId "
            + "left join CommentHeart _heart on _heart.commentId = _comment.id and _heart.memberId = :memberId "
            + "left join CommentHeartCount _heartCount on _heartCount.commentId = _comment.id "
            + "where _comment.feedId = :feedId and _comment.deleted = false")
    Slice<MemberCommentSummary> findWithMemberAllByFeedIdAndMemberId(String feedId, String memberId, Pageable pageable);
}
