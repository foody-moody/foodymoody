package com.foodymoody.be.feed_collection.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection.infra.persistence.jpa.FeedCollectionMoodJpaRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class FeedCollectionRepositoryTest {

    @Autowired
    private FeedCollectionRepository feedCollectionRepository;
    @Autowired
    private FeedCollectionMoodJpaRepository moodRepository;

    @DisplayName("무드를 포함한 피드 컬렉션 저장하면 무드도 함께 저장해야 한다")
    @Test
    void save() {
        // given
        var mood = moodRepository.save(
                new FeedCollectionMood(IdFactory.createFeedCollectionMoodId(), "mood", LocalDateTime.now()));

        var feedCollection = new FeedCollection(
                IdFactory.createFeedCollectionId(),
                IdFactory.createMemberId(),
                "url",
                "title",
                "description",
                0,
                false,
                false,
                Collections.EMPTY_LIST,
                List.of(mood),
                LocalDateTime.now()
        );
        // when
        var save = feedCollectionRepository.save(feedCollection);

        // then
        assertThat(save.getMoods()).hasSize(1);
    }
}
