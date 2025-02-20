package com.foodymoody.be.feed_collection_reply_like.application.service;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyLikeId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_reply_like.domain.FeedCollectionReplyLike;
import com.foodymoody.be.feed_collection_reply_like.domain.FeedCollectionReplyLikeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionReplyLikeWriteService {

    private final FeedCollectionReplyLikeRepository repository;

    @Transactional
    public FeedCollectionReplyLikeId post(
            FeedCollectionReplyId replyId,
            FeedCollectionCommentId commentId,
            MemberId memberId
    ) {
        var id = IdFactory.createFeedCollectionReplyLikeId();
        var createdAt = LocalDateTime.now();
        var like = new FeedCollectionReplyLike(id, memberId, replyId, commentId, createdAt);
        return repository.save(like).getId();
    }

    @Transactional
    public void cancel(MemberId memberId, FeedCollectionReplyId id) {
        repository.deleteByFeedCollectionReplyIdAndMemberId(id, memberId);
    }

}
