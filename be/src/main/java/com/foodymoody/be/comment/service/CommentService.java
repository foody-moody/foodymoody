package com.foodymoody.be.comment.service;

import com.foodymoody.be.comment.controller.dto.EditCommentRequest;
import com.foodymoody.be.comment.controller.dto.RegisterCommentRequest;
import com.foodymoody.be.comment.controller.dto.ReplyResponse;
import com.foodymoody.be.comment.domain.Comment;
import com.foodymoody.be.comment.domain.CommentId;
import com.foodymoody.be.comment.domain.Reply;
import com.foodymoody.be.comment.domain.ReplyId;
import com.foodymoody.be.comment.repository.CommentRepository;
import com.foodymoody.be.common.exception.CommentNotExistsException;
import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.service.FeedService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final FeedService feedService;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentId registerComment(RegisterCommentRequest request, String memberId) {
        feedService.validate(request.getFeedId());
        String newId = IdGenerator.generate();
        LocalDateTime now = LocalDateTime.now();
        CommentId commentId = new CommentId(newId);
        Comment comment = commentMapper.toEntity(request, now, commentId, memberId);
        Comment saved = commentRepository.save(comment);
        return saved.getId();
    }

    @Transactional
    public void edit(String id, EditCommentRequest request, String memberId) {
        CommentId commentId = new CommentId(id);
        Comment comment = fetchById(commentId);
        String content = request.getContent();
        comment.edit(memberId, content, LocalDateTime.now());
    }

    @Transactional
    public void delete(String id, String memberId) {
        CommentId commentId = new CommentId(id);
        Comment comment = fetchById(commentId);
        comment.delete(memberId, LocalDateTime.now());
    }

    public Comment fetchById(CommentId id) {
        return commentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
    }

    @Transactional
    public void reply(String id, RegisterCommentRequest request, String memberId) {
        Comment comment = fetchById(CommentId.from(id));
        ReplyId replyId = ReplyId.from(IdGenerator.generate());
        Reply reply = commentMapper.toReply(replyId, LocalDateTime.now(), memberId, request.getContent());
        comment.addReply(reply);
    }

    public Slice<ReplyResponse> fetchAllReplay(String id, Pageable pageable) {
        CommentId commentId = new CommentId(id);
        return commentRepository.findReplyWithMemberAllById(commentId, pageable);
    }
}
