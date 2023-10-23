package com.foodymoody.be.comment.unit;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;

public class CommentMapper {

    private CommentMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Comment toEntity(RegisterCommentRequest request, long id) {
        return new Comment(id, request.getContent(), request.getFeedId());
    }
}
