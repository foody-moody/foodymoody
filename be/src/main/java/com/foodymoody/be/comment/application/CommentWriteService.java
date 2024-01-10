package com.foodymoody.be.comment.application;

import com.foodymoody.be.comment.application.dto.request.EditCommentRequest;
import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.comment.application.dto.request.RegisterReplyRequest;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.comment.domain.entity.Reply;
import com.foodymoody.be.comment.domain.repository.CommentRepository;
import com.foodymoody.be.common.exception.CommentNotExistsException;
import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.ReplyId;
import java.time.LocalDateTime;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentWriteService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentId registerComment(RegisterCommentRequest request, MemberId memberId) {
        String newId = IdGenerator.generate();
        LocalDateTime now = LocalDateTime.now();
        CommentId commentId = new CommentId(newId);
        FeedId feedId = new FeedId(request.getFeedId());
        Comment comment = commentMapper.toEntity(request, feedId, now, commentId, memberId);
        Comment saved = commentRepository.save(comment);
        return saved.getId();
    }

    @Transactional
    public void edit(CommentId id, EditCommentRequest request, MemberId memberId) {
        Comment comment = fetchById(id);
        String content = request.getContent();
        comment.edit(memberId, content, LocalDateTime.now());
    }

    @Transactional
    public void delete(CommentId id, MemberId memberId) {
        Comment comment = fetchById(id);
        comment.delete(memberId, LocalDateTime.now());
    }

    @Transactional
    public ReplyId reply(CommentId id, @Valid RegisterReplyRequest request, MemberId memberId) {
        Comment comment = fetchById(id);
        ReplyId replyId = IdFactory.createReplyId();
        LocalDateTime now = LocalDateTime.now();
        Reply reply = commentMapper.toReply(replyId, now, memberId, request.getContent());
        comment.addReply(reply, now);
        return reply.getId();
    }

    private Comment fetchById(CommentId id) {
        return commentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
    }
}
