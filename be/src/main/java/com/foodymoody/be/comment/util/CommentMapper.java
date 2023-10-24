package com.foodymoody.be.comment.util;

import static com.foodymoody.be.common.util.Constants.UTILITY_CLASS;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;

public class CommentMapper {

    private CommentMapper() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static Comment toEntity(RegisterCommentRequest request, String id) {
        return new Comment(id, request.getContent(), request.getFeedId());
    }
}
