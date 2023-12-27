package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "feed_collection")
public class FeedCollection {

    @Getter
    @Id
    private FeedCollectionId id;
    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "author_id"))
    private MemberId authorId;
    @Getter
    private String thumbnailUrl;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private int heartCount;
    @Getter
    private int followerCount;
    @Getter
    private boolean isPrivate;
    @Getter
    private boolean isDeleted;
    @Embedded
    private FeedIds feedIds;
    @Embedded
    private CommentIds commentIds;
    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private FeedCollectionMoods moods;
    @Getter
    private LocalDateTime createdAt;
    @Getter
    private LocalDateTime updatedAt;

    public FeedCollection(
            FeedCollectionId id, MemberId memberId, String thumbnailUrl, String title, String description,
            int followerCount, boolean isPrivate, boolean isDeleted, List<FeedId> feedIds,
            List<FeedCollectionMood> moods, LocalDateTime createdAt
    ) {
        this.id = id;
        this.authorId = memberId;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.description = description;
        this.followerCount = followerCount;
        this.isPrivate = isPrivate;
        this.isDeleted = isDeleted;
        this.feedIds = new FeedIds(feedIds);
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.commentIds = new CommentIds();
        this.moods = new FeedCollectionMoods(IdFactory.createFeedCollectionMoodsId(), moods, createdAt);
        Events.publish(FeedCollectionAddedEvent.of(id, createdAt));
    }

    public List<FeedId> getFeedIds() {
        return feedIds.getIds();
    }

    public List<FeedCollectionCommentId> getCommentIds() {
        return commentIds.getIds();
    }

    public void addCommentId(FeedCollectionCommentId collectionCommentId) {
        commentIds.add(collectionCommentId);
    }

    public void removeCommentId(FeedCollectionCommentId collectionCommentId) {
        commentIds.remove(collectionCommentId);
    }

    public List<FeedCollectionMood> getMoods() {
        return moods.getMoodList();
    }

    public void addMood(MemberId memberId, FeedCollectionMood mood) {
        if (!memberId.equals(authorId)) {
            throw new IllegalArgumentException("피드 컬렉션 작성자가 아닙니다.");
        }
        moods.add(mood);
    }

    public void removeMood(MemberId memberId, FeedCollectionMood mood) {
        if (!memberId.equals(authorId)) {
            throw new IllegalArgumentException("피드 컬렉션 작성자가 아닙니다.");
        }
        moods.remove(mood);
    }
}
