package com.foodymoody.be.comment.service;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import java.time.LocalDateTime;

public class CommentMapper {

    private CommentMapper() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static Comment toEntity(RegisterCommentRequest request, LocalDateTime createdAt, CommentId commentId) {
        return new Comment(commentId, request.getContent(), request.getFeedId(), createdAt, false);
    }
}
