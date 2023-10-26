package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.EditCommentRequest;
import com.foodymoody.be.comment.controller.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.repository.CommentRepository;
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

    @Transactional
    public String registerComment(RegisterCommentRequest request) {
        feedService.validate(request.getFeedId());
        String newId = IdGenerator.generate();
        LocalDateTime now = LocalDateTime.now();
        Comment comment = CommentMapper.toEntity(request, newId, now);
        Comment saved = commentRepository.save(comment);
        return saved.getId();
    }

    @Transactional
    public void edit(String id, EditCommentRequest request) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        String content = request.getContent();
        comment.edit(content, LocalDateTime.now());
    }
}
