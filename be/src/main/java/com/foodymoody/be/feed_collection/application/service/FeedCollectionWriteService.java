package com.foodymoody.be.feed_collection.application.service;

import com.foodymoody.be.common.exception.FeedCollectionNotFoundException;
import com.foodymoody.be.common.exception.PermissionDeniedAccessException;
import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollection;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import com.foodymoody.be.feed_collection.domain.FeedCollectionRepository;
import com.foodymoody.be.feed_collection_like_count.domain.FeedCollectionLikeCount;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionWriteService {

    private final FeedCollectionRepository repository;

    @Transactional
    public FeedCollectionId create(
            String title,
            String description,
            boolean isPrivate,
            MemberId memberId,
            List<FeedCollectionMood> moods
    ) {
        var id = IdFactory.createFeedCollectionId();
        LocalDateTime now = LocalDateTime.now();
        var feedCollection = new FeedCollection(
                id,
                memberId,
                title,
                description,
                0,
                isPrivate,
                false,
                moods,
                now
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
        return repository.findById(feedCollectionId).orElseThrow(FeedCollectionNotFoundException::new);
    }

    @Transactional
    public void addCommentId(FeedCollectionId feedCollectionId, FeedCollectionCommentId collectionCommentId) {
        var feedCollection = fetchById(feedCollectionId);
        if (feedCollection.isPrivate()) {
            throw new PermissionDeniedAccessException();
        }
        feedCollection.addCommentId(collectionCommentId);
    }

    @Transactional
    public void removeCommentId(FeedCollectionId feedCollectionId, FeedCollectionCommentId collectionCommentId) {
        var feedCollection = fetchById(feedCollectionId);
        if (feedCollection.isPrivate()) {
            throw new PermissionDeniedAccessException();
        }
        feedCollection.removeCommentId(collectionCommentId);
    }

    @Transactional
    public void edit(
            FeedCollectionId id, String title, Content content, List<FeedCollectionMood> moods,
            MemberId memberId
    ) {
        FeedCollection feedCollection = fetchById(id);
        feedCollection.edit(title, content, moods, memberId, LocalDateTime.now());
    }

    @Transactional
    public void update(FeedCollectionId id, List<FeedId> feedIds, MemberId memberId, String thumbnailUrl) {
        FeedCollection feedCollection = fetchById(id);
        feedCollection.update(feedIds, memberId, LocalDateTime.now(), thumbnailUrl);
    }

    @Transactional
    public void delete(FeedCollectionId id, MemberId memberId) {
        FeedCollection feedCollection = fetchById(id);
        feedCollection.delete(memberId, LocalDateTime.now());
    }

    @Transactional
    public void addFeed(FeedCollectionId id, FeedId feedId, MemberId memberId, String thumbnailUrl) {
        FeedCollection feedCollection = fetchById(id);
        feedCollection.addFeed(feedId, memberId, thumbnailUrl, LocalDateTime.now());
    }

    @Transactional
    public void removeFeed(FeedCollectionId id, FeedId feedId, MemberId memberId) {
        FeedCollection feedCollection = fetchById(id);
        feedCollection.removeFeed(feedId, memberId, LocalDateTime.now());
    }

    @Transactional
    public void updateLikeCount(List<FeedCollectionLikeCount> all) {
        all.forEach(likeCount -> {
            var feedCollection = fetchById(likeCount.getFeedCollectionId());
            feedCollection.updateLikeCount(likeCount.getLikeCount());
        });
    }

}
