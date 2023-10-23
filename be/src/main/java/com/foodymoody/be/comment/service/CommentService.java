package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.common.exception.ContentIsEmptyException;
import com.foodymoody.be.common.exception.ContentIsOver200Exception;
import com.foodymoody.be.common.exception.ContentIsSpaceException;
import com.foodymoody.be.common.exception.ContentNotExistsException;
import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public void registerComment(RegisterCommentRequest request) {
        if (request.getContent() == null) {
            throw new ContentNotExistsException();
        }
        if (request.getContent().isEmpty()) {
            throw new ContentIsEmptyException();
        }
        if (request.getContent().isBlank()) {
            throw new ContentIsSpaceException();
        }
        if (request.getContent().length() > 200) {
            throw new ContentIsOver200Exception();
        }
        if (request.getFeedId() == 0) {
            throw new FeedIdNotExistsException();
        }
    }
}
