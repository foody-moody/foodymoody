package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "feed_collection_mood",
        indexes = {
                @Index(name = "feed_collection_mood_name_idx", columnList = "name")
        }
)
public class FeedCollectionMood {

    @Getter
    @Id
    private FeedCollectionMoodId id;

    @Getter
    private String name;

    @Getter
    private LocalDateTime createdAt;

    @Getter
    private LocalDateTime updatedAt;

    public FeedCollectionMood(FeedCollectionMoodId id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

}
