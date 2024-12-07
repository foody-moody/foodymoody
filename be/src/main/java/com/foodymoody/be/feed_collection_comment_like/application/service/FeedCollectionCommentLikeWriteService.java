package com.foodymoody.be.feed_collection_comment_like.application.service;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLike;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLikeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeWriteService {

    private final FeedCollectionCommentLikeRepository repository;

    @Transactional
    public FeedCollectionCommentLikeId post(FeedCollectionCommentId commentId, MemberId memberId) {
        var id = IdFactory.createFeedCollectionCommentLikeId();
        var createdAt = LocalDateTime.now();
        var commentLike = new FeedCollectionCommentLike(id, commentId, memberId, createdAt);
        return repository.save(commentLike).getId();
    }

    @Transactional
    public void cancel(FeedCollectionCommentId commentId, MemberId memberId) {
        repository.deleteByCommentIdAndMemberId(commentId, memberId);
    }

}
