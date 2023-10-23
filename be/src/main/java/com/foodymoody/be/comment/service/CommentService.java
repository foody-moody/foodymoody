package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public void registerComment(RegisterCommentRequest request) {
        if (request.getContent() == null || request.getContent().isBlank()) {
            throw new ContentNotExistsException();
        }
    }
}
