package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.dto.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import com.foodymoody.be.comment.domain.Reply;
import com.foodymoody.be.comment.domain.ReplyId;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(RegisterCommentRequest request, LocalDateTime createdAt, CommentId commentId,
            String memberId) {
        return new Comment(commentId, request.getContent(), request.getFeedId(), false, memberId, createdAt);
    }


    public Reply toReply(ReplyId replyId, LocalDateTime now, String memberId, @NotNull @NotBlank String content) {
        return new Reply(replyId, content, false, memberId, now, now);
    }
}
