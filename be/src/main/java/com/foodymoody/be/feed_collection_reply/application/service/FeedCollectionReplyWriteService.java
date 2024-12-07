package com.foodymoody.be.feed_collection_reply.application.service;

import com.foodymoody.be.common.exception.FeedCollectionReplyNotFoundException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReply;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyWriteService {

    private final FeedCollectionReplyRepository repository;

    @Transactional
    public FeedCollectionReplyId post(FeedCollectionCommentId commentId, Content content, MemberId memberId) {
        var id = IdFactory.createFeedCollectionReplyId();
        var createdAt = LocalDateTime.now();
        var reply = new FeedCollectionReply(id, commentId, memberId, content, createdAt);
        return repository.save(reply).getId();
    }

    @Transactional
    public void delete(FeedCollectionReplyId replyId, MemberId memberId) {
        var reply = fetchById(replyId);
        var updatedAt = LocalDateTime.now();
        reply.delete(memberId, updatedAt);
    }

    @Transactional
    public void edit(FeedCollectionReplyId replyId, Content content, MemberId memberId) {
        var reply = fetchById(replyId);
        var updatedAt = LocalDateTime.now();
        reply.edit(content, memberId, updatedAt);
    }

    public FeedCollectionReply fetchById(FeedCollectionReplyId replyId) {
        return repository.findById(replyId)
                .orElseThrow(FeedCollectionReplyNotFoundException::new);
    }

}
