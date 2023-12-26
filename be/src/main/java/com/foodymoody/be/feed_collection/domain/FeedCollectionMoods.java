package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionMoodsId;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCollectionMoods {

    @Id
    private FeedCollectionMoodsId id;
    @Getter
    @ManyToMany
    private List<FeedCollectionMood> moodList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollectionMoods(FeedCollectionMoodsId id, List<FeedCollectionMood> moodList, LocalDateTime createdAt) {
        this.id = id;
        this.moodList = moodList;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public void add(FeedCollectionMood mood) {
        moodList.add(mood);
    }

    public void remove(FeedCollectionMood mood) {
        moodList.remove(mood);
    }
}
