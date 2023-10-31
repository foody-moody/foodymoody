package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.controller.dto.CommentResponse;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import java.time.LocalDateTime;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentResponse toResponse(Comment comment) {
        return CommentResponse.of(comment.getId().getId(), comment.getContent(), comment.isDeleted(),
                comment.getCreatedAt(), comment.getUpdatedAt());
    }

    public Comment toEntity(RegisterCommentRequest request, LocalDateTime createdAt, CommentId commentId) {
        return new Comment(commentId, request.getContent(), request.getFeedId(), createdAt, false);
    }

    public Slice<CommentResponse> toResponse(Slice<Comment> comments) {
        return comments.map(this::toResponse);
    }
}
