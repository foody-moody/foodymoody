package com.foodymoody.be.feed_comment.application.service;

import com.foodymoody.be.common.exception.CommentNotExistsException;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.data.EditFeedCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedCommentData;
import com.foodymoody.be.feed_comment.application.dto.data.RegisterFeedReplyData;
import com.foodymoody.be.feed_comment.application.mapper.FeedCommentMapper;
import com.foodymoody.be.feed_comment.domain.entity.FeedComment;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.repository.FeedCommentRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCommentWriteService {

    private final FeedCommentRepository feedCommentRepository;

    @Transactional
    public FeedCommentId register(RegisterFeedCommentData data) {
        var commentId = IdFactory.createFeedCommentId();
        var now = LocalDateTime.now();
        var comment = FeedCommentMapper.toFeedComment(data, commentId, now);
        var saved = feedCommentRepository.save(comment);
        return saved.getId();
    }

    @Transactional
    public void edit(EditFeedCommentData data) {
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
    public FeedReplyId reply(RegisterFeedReplyData data) {
        var comment = fetchById(data.getFeedCommentId());
        var replyId = IdFactory.createFeedReplyId();
        var now = LocalDateTime.now();
        var reply = FeedCommentMapper.toFeedReply(replyId, data, now);
        comment.addReply(reply, now);
        return reply.getId();
    }

    public void deleteReply(FeedCommentId commentId, FeedReply feedReply, LocalDateTime updatedAt) {
        var comment = fetchById(commentId);
        comment.deleteReply(feedReply, updatedAt);
    }

    private FeedComment fetchById(FeedCommentId id) {
        return feedCommentRepository.findById(id).orElseThrow(CommentNotExistsException::new);
    }

}
