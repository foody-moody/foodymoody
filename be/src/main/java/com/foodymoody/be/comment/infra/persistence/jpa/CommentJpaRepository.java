package com.foodymoody.be.comment.infra.persistence.jpa;

import com.foodymoody.be.comment.application.dto.response.MemberCommentSummary;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentJpaRepository extends JpaRepository<Comment, CommentId> {

    @Query("select _comment.id.value as id,_comment.content as content,"
            + "_member.id.value as memberId,_member.nickname as nickname,_image.url as imageUrl,_comment.hasReply as hasReply,"
            + "_reply.commentList.size as replyCount,_comment.createdAt as createdAt,_comment.updatedAt as updatedAt, "
            + "_heartCount.count as heartCount "
            + "from Comment _comment "
            + "left join _comment.replyComments _reply "
            + "left join Member _member on _member.id = _comment.memberId "
            + "left join Image _image on _image.id = _member.profileImageId "
            + "left join CommentHeartCount _heartCount on _heartCount.commentId = _comment.id "
            + "where _comment.feedId = :feedId and _comment.deleted = false")
    Slice<MemberCommentSummary> findWithMemberAllByFeedId(
            @Param("feedId") FeedId feedId,
            @Param("pageable") Pageable pageable
    );

    @Query("select _comment.id.value as id,_comment.content as content,"
            + "_member.id.value as memberId,_member.nickname as nickname,_image.url as imageUrl,_comment.hasReply as hasReply,"
            + "_reply.commentList.size as replyCount,_comment.createdAt as createdAt,_comment.updatedAt as updatedAt, "
            + "_heartCount.count as heartCount, "
            + "(case when _heart is not null then true else false end) as hearted "
            + "from Comment _comment "
            + "left join _comment.replyComments _reply "
            + "left join Member _member on _member.id = _comment.memberId "
            + "left join Image _image on _image.id = _member.profileImageId "
            + "left join CommentHeart _heart on _heart.commentId = _comment.id and _heart.memberId = :memberId "
            + "left join CommentHeartCount _heartCount on _heartCount.commentId = _comment.id "
            + "where _comment.feedId = :feedId and _comment.deleted = false")
    Slice<MemberCommentSummary> findWithMemberAllByFeedIdAndMemberId(FeedId feedId, MemberId memberId,
            Pageable pageable);
}
