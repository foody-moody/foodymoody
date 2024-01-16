package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.data.EditCommentData;
import com.foodymoody.be.comment.application.dto.data.RegisterCommentData;
import com.foodymoody.be.comment.application.dto.data.RegisterReplyData;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import com.foodymoody.be.common.exception.CommentNotExistsException;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentWriteService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentId register(RegisterCommentData data) {
        var commentId = IdFactory.createCommentId();
        var now = LocalDateTime.now();
        var comment = commentMapper.toEntity(data, commentId, now);
        var saved = commentRepository.save(comment);
        return saved.getId();
    }

    @Transactional
    public void edit(EditCommentData data) {
        var comment = fetchById(data.getCommentId());
        var content = data.getContent();
        var memberId = data.getMemberId();
        var now = LocalDateTime.now();
        comment.edit(memberId, content, now);
    }

    @Transactional
    public void delete(CommentId id, MemberId memberId) {
        Comment comment = fetchById(id);
        comment.delete(memberId, LocalDateTime.now());
    }

    @Transactional
    public ReplyId reply(RegisterReplyData data) {
        var comment = fetchById(data.getCommentId());
        var replyId = IdFactory.createReplyId();
        var now = LocalDateTime.now();
        var reply = commentMapper.toReply(replyId, data, now);
        comment.addReply(reply, now);
        return reply.getId();
    }

    private Comment fetchById(CommentId id) {
        return commentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
    }
}
