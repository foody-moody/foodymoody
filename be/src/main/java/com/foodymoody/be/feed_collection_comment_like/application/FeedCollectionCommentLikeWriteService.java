package com.foodymoody.be.feed_collection_comment_like.application;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLike;
import com.foodymoody.be.feed_collection_comment_like.domain.FeedCollectionCommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FeedCollectionCommentLikeWriteService {

    private final FeedCollectionCommentLikeRepository repository;

    public FeedCollectionCommentLikeId like(FeedCollectionCommentId commentId, MemberId memberId) {
        var id = IdFactory.createFeedCollectionCommentLikeId();
        var commentLike = new FeedCollectionCommentLike(id, commentId, memberId);
        return repository.save(commentLike).getId();
    }
}
