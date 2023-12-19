package com.foodymoody.be.feed_collection.application;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionWriterService {

    private final FeedCollectionRepository repository;

    public FeedCollectionId createCollection(
            String title,
            String description,
            String thumbnailUrl,
            boolean isPrivate,
            MemberId memberId,
            List<FeedId> feedIds
    ) {
        var id = IdFactory.createFeedCollectionId();
        LocalDateTime now = LocalDateTime.now();
        var feedCollection = new FeedCollection(
                id, memberId, thumbnailUrl, title, description, 0, isPrivate, false, feedIds
                , now, now
        );
        return repository.save(feedCollection).getId();
    }

    public void checkExistence(FeedCollectionId feedCollectionId) {
        if (repository.existsById(feedCollectionId)) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 피드 컬렉션입니다.");
    }

    public FeedCollection fetchById(FeedCollectionId feedCollectionId) {
        return repository.findById(feedCollectionId).orElseThrow();
    }

    @Transactional
    public void addCommentId(FeedCollectionId feedCollectionId, FeedCollectionCommentId collectionCommentId) {
        FeedCollection feedCollection = fetchById(feedCollectionId);
        feedCollection.addCommentId(collectionCommentId);
    }

    public void removeCommentId(FeedCollectionId feedCollectionId, FeedCollectionCommentId collectionCommentId) {
        FeedCollection feedCollection = fetchById(feedCollectionId);
        feedCollection.removeCommentId(collectionCommentId);
    }
}