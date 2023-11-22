package com.foodymoody.be.comment.repository;

import com.foodymoody.be.comment.controller.dto.ReplyResponse;
import com.foodymoody.be.comment.domain.CommentId;
import com.foodymoody.be.comment.domain.Reply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, CommentId> {

    @Query("select new com.foodymoody.be.comment.controller.dto.ReplyResponse(_reply.id.id,_reply.content,"
            + "_member.id.id,_member.nickname,_image.url,"
            + "_reply.createdAt ,_reply.updatedAt) "
            + "from Reply _reply "
            + "inner join Comment _comment "
            + "on _comment.id = :commentId "
            + "join Member _member "
            + "on _member.id.id = _reply.memberId "
            + "left join Image _image "
            + "on _image.id = _member.profileImageId ")
    Slice<ReplyResponse> findReplyWithMemberAllById(CommentId commentId, Pageable pageable);
}
