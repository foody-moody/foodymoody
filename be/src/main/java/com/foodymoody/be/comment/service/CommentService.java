package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.EditCommentRequest;
import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import com.foodymoody.be.comment.repository.CommentRepository;
import com.foodymoody.be.common.exception.CommentNotExistsException;
import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.service.FeedService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final FeedService feedService;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentId registerComment(RegisterCommentRequest request) {
        feedService.validate(request.getFeedId());
        String newId = IdGenerator.generate();
        LocalDateTime now = LocalDateTime.now();
        CommentId commentId = new CommentId(newId);
        Comment comment = commentMapper.toEntity(request, now, commentId);
        Comment saved = commentRepository.save(comment);
        return saved.getId();
    }

    @Transactional
    public void edit(String id, EditCommentRequest request) {
        CommentId commentId = new CommentId(id);
        Comment comment = fetchById(commentId);
        String content = request.getContent();
        comment.edit(content, LocalDateTime.now());
    }

    @Transactional
    public void delete(String id) {
        CommentId commentId = new CommentId(id);
        Comment comment = fetchById(commentId);
        comment.delete(LocalDateTime.now());
    }

    public Comment fetchById(CommentId id) {
        return commentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
    }
}
