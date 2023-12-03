package com.foodymoody.be.comment.infra.persistence.jpa;

import com.foodymoody.be.comment.application.dto.response.MemberReplySummary;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.comment.domain.entity.ReplyId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyJpaRepository extends JpaRepository<Reply, ReplyId> {

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
}
