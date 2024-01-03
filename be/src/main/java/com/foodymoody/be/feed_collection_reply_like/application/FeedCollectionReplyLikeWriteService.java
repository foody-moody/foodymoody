package com.foodymoody.be.feed_collection_reply_like.application;

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
    public FeedCollectionReplyLikeId post(FeedCollectionReplyId replyId, MemberId memberId) {
        var id = IdFactory.createFeedCollectionReplyLikeId();
        var like = new FeedCollectionReplyLike(id, memberId, replyId, LocalDateTime.now());
        return repository.save(like).getId();
    }

    @Transactional
    public void cancel(MemberId memberId, FeedCollectionReplyLikeId id) {
        repository.deleteByIdAndMemberId(id, memberId);
    }
}
