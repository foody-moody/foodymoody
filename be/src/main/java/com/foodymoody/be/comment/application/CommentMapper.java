package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.entity.CommentId;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.comment.domain.entity.ReplyId;
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
