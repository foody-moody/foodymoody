package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public void registerComment(RegisterCommentRequest request) {
        if (request.getContent() == null) {
            throw new ContentNotExistsException();
        }
    }
}
