package com.foodymoody.be.feed_comment.application;

import com.foodymoody.be.common.exception.CommentNotExistsException;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.data.EditCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterReplyData;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.repository.CommentRepository;
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
    public FeedCommentId register(RegisterCommentData data) {
        var commentId = IdFactory.createFeedCommentId();
        var now = LocalDateTime.now();
        var comment = commentMapper.toEntity(data, commentId, now);
        var saved = commentRepository.save(comment);
        return saved.getId();
    }

    @Transactional
    public void edit(EditCommentData data) {
        var comment = fetchById(data.getFeedCommentId());
        var content = data.getContent();
        var memberId = data.getMemberId();
        var now = LocalDateTime.now();
        comment.edit(memberId, content, now);
    }

    @Transactional
    public void delete(FeedCommentId id, MemberId memberId) {
        FeedComment feedComment = fetchById(id);
        feedComment.delete(memberId, LocalDateTime.now());
    }

    @Transactional
    public FeedReplyId reply(RegisterReplyData data) {
        var comment = fetchById(data.getFeedCommentId());
        var replyId = IdFactory.createFeedReplyId();
        var now = LocalDateTime.now();
        var reply = commentMapper.toReply(replyId, data, now);
        comment.addReply(reply, now);
        return reply.getId();
    }

    private FeedComment fetchById(FeedCommentId id) {
        return commentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
    }
}
