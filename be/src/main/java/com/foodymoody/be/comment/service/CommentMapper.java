package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.dto.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public Comment toEntity(RegisterCommentRequest request, LocalDateTime createdAt, CommentId commentId,
            String memberId) {
        return new Comment(commentId, request.getContent(), request.getFeedId(), false, memberId, createdAt);
    }
}
