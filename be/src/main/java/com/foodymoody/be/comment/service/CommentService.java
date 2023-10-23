package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.repository.CommentRepository;
import com.foodymoody.be.comment.util.CommentMapper;
import com.foodymoody.be.comment.util.CommentValidator;
import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final FeedService feedService;
    private final CommentRepository commentRepository;

    public String registerComment(RegisterCommentRequest request) {
        CommentValidator.validate(request);
        if (!feedService.exists(request.getFeedId())) {
            throw new FeedIdNotExistsException();
        }
        Comment comment = CommentMapper.toEntity(request, IdGenerator.generate());
        Comment saved = commentRepository.save(comment);
        return saved.getId();
    }
}
