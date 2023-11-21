package com.foodymoody.be.comment.repository;

import com.foodymoody.be.comment.controller.dto.CommentResponse;
import com.foodymoody.be.comment.controller.dto.ReplyResponse;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {

    @Query("select new com.foodymoody.be.comment.controller.dto.CommentResponse(_comment.id.id,_comment.content,"
            + "_member.wrappedId.id,_member.nickname,_image.url,_comment.hasReply,"
            + "_comment.createdAt ,_comment.updatedAt) "
            + "from Comment _comment "
            + "left join Member _member "
            + "on _member.wrappedId.id = _comment.memberId "
            + "left join Image _image "
            + "on _image.id = _member.profileImageId "
            + "where _comment.feedId = :feedId and _comment.deleted = false")
    Slice<CommentResponse> findWithMemberAllByFeedId(String feedId, Pageable pageable);

    @Query("select new com.foodymoody.be.comment.controller.dto.ReplyResponse(_reply.id.id,_reply.content,"
            + "_member.wrappedId.id,_member.nickname,_image.url,"
            + "_reply.createdAt ,_reply.updatedAt) "
            + "from Comment _comment "
            + "left join _comment.replyComments.commentList _reply "
            + "left join Member _member "
            + "on _member.wrappedId.id = _reply.memberId "
            + "left join Image _image "
            + "on _image.id = _member.profileImageId "
            + "where _comment.id = :commentId")
    Slice<ReplyResponse> findReplyWithMemberAllById(CommentId commentId, Pageable pageable);
}
