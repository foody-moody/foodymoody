package com.foodymoody.be.feed_comment.application.service;

import com.foodymoody.be.common.exception.FeedReplyNotFoundException;
import com.foodymoody.be.common.util.ids.FeedReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.data.UpdateFeedReplyData;
import com.foodymoody.be.feed_comment.domain.entity.FeedReply;
import com.foodymoody.be.feed_comment.domain.repository.FeedReplyRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedReplyWriteService {

    private final FeedReplyRepository repository;

    @Transactional
    public void update(UpdateFeedReplyData data) {
        var feedReply = fetchById(data.getFeedReplyId());
        LocalDateTime updatedAt = LocalDateTime.now();
        feedReply.update(data.getMemberId(), data.getContent(), updatedAt);
    }

    @Transactional
    public void delete(FeedReplyId feedReplyId, MemberId memberId) {
        var feedReply = fetchById(feedReplyId);
        LocalDateTime updatedAt = LocalDateTime.now();
        feedReply.delete(memberId, updatedAt);
    }

    public FeedReply fetchById(FeedReplyId feedReplyId) {
        return repository.findById(feedReplyId)
                .orElseThrow(FeedReplyNotFoundException::new);
    }

}
