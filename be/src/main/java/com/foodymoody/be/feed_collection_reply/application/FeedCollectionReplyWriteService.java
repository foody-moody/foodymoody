package com.foodymoody.be.feed_collection_reply.application;

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
        var reply = new FeedCollectionReply(id, commentId, memberId, content, LocalDateTime.now());
        return repository.save(reply).getId();
    }

    @Transactional
    public void delete(FeedCollectionReplyId replyId, MemberId memberId) {
        var reply = getReply(replyId);
        reply.delete(memberId, LocalDateTime.now());
    }

    @Transactional
    public void edit(FeedCollectionReplyId replyId, Content content, MemberId memberId) {
        var reply = getReply(replyId);
        reply.edit(content, memberId, LocalDateTime.now());
    }

    private FeedCollectionReply getReply(FeedCollectionReplyId replyId) {
        return repository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대댓글입니다."));
    }
}
