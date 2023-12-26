package com.foodymoody.be.feed_collection.application;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMood;
import com.foodymoody.be.feed_collection.domain.FeedCollectionMoodRepository;
import com.foodymoody.be.feed_collection.domain.FeedCollectionRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedCollectionMoodWriteService {

    private final FeedCollectionRepository feedCollectionRepository;
    private final FeedCollectionMoodRepository feedCollectionMoodRepository;

    @Transactional
    public void addMood(FeedCollectionId feedCollectionId, MemberId memberId, FeedCollectionMoodId moodId) {
        var feedCollection = feedCollectionRepository.findById(feedCollectionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드 컬렉션입니다."));
        var mood = feedCollectionMoodRepository.findById(moodId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드 컬렉션 감정입니다."));
        feedCollection.addMood(memberId, mood);
    }

    @Transactional
    public void removeMood(FeedCollectionId feedCollectionId, MemberId memberId, FeedCollectionMoodId moodId) {
        var feedCollection = feedCollectionRepository.findById(feedCollectionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드 컬렉션입니다."));
        var mood = feedCollectionMoodRepository.findById(moodId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드 컬렉션 감정입니다."));
        feedCollection.removeMood(memberId, mood);
    }

    @Transactional
    public FeedCollectionMoodId create(String name) {
        var id = IdFactory.createFeedCollectionMoodId();
        var mood = new FeedCollectionMood(id, name, LocalDateTime.now());
        feedCollectionMoodRepository.save(mood);
        return id;
    }
}
