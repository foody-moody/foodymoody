package com.foodymoody.be.feed_collection_like.application.service;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection_like.domain.FeedCollectionLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionLikeReadService {

    private final FeedCollectionLikeRepository repository;

    @Transactional(readOnly = true)
    public boolean isLiked(FeedCollectionId feedCollectionId, MemberId memberId) {
        return repository.existsByFeedCollectionIdAndMemberId(feedCollectionId, memberId);
    }

}
