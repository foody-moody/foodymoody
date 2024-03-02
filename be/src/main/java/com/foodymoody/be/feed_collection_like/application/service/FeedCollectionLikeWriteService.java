package com.foodymoody.be.feed_collection_like.application.service;

import com.foodymoody.be.common.exception.FeedCollectionLikeIsAlreadyExistException;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionLikeId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLike;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLikeRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeWriteService {

    private final FeedCollectionLikeRepository repository;

    @Transactional
    public FeedCollectionLikeId post(FeedCollectionId feedCollectionId, MemberId memberId) {
        repository.findByFeedCollectionIdAndMemberId(feedCollectionId, memberId).ifPresent(like -> {
            throw new FeedCollectionLikeIsAlreadyExistException();
        });
        var id = IdFactory.createFeedCollectionLikeId();
        var like = new FeedCollectionLike(id, feedCollectionId, memberId, LocalDateTime.now());
        return repository.save(like).getId();
    }

    @Transactional
    public void cancel(FeedCollectionLikeId likeId, MemberId memberId) {
        repository.deleteByIdAndMemberId(likeId, memberId);
    }
}
