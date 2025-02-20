package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.event.EventManager;
import com.foodymoody.be.common.util.Content;
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
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        name = "feed_collection",
        indexes = {
                @Index(name = "idx_feed_collection_created_at", columnList = "created_at")
        }
)
public class FeedCollection {

    @Getter
    @Id
    private FeedCollectionId id;

    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "author_id"))
    private MemberId authorId;

    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private int likeCount;

    @Getter
    private int followerCount;

    @Getter
    private String thumbnailUrl;

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
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Getter
    private LocalDateTime updatedAt;

    public FeedCollection(
            FeedCollectionId id,
            MemberId memberId,
            String title,
            String description,
            int followerCount,
            boolean isPrivate,
            boolean isDeleted,
            List<FeedCollectionMood> moods,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.authorId = memberId;
        this.title = title;
        this.description = description;
        this.followerCount = followerCount;
        this.isPrivate = isPrivate;
        this.isDeleted = isDeleted;
        this.feedIds = new FeedIds();
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.commentIds = new CommentIds();
        this.moods = new FeedCollectionMoods(IdFactory.createFeedCollectionMoodsId(), moods, createdAt);
        EventManager.raise(FeedCollectionAddedEvent.of(
                memberId,
                id,
                title,
                thumbnailUrl,
                createdAt
        ));
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
        validateAuthor(memberId);
        moods.add(mood);
    }

    public void removeMood(MemberId memberId, FeedCollectionMood mood) {
        validateAuthor(memberId);
        moods.remove(mood);
    }

    public void edit(
            String title,
            Content content,
            List<FeedCollectionMood> moodIds,
            MemberId memberId,
            LocalDateTime updatedAt
    ) {
        validateAuthor(memberId);
        this.title = title;
        this.description = content.getValue();
        this.moods.update(moodIds);
        this.updatedAt = updatedAt;
    }

    public void update(List<FeedId> feedIds, MemberId memberId, LocalDateTime updatedAt, String thumbnailUrl) {
        validateAuthor(memberId);
        this.feedIds.update(feedIds);
        this.updatedAt = updatedAt;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void delete(MemberId memberId, LocalDateTime updatedAt) {
        validateAuthor(memberId);
        this.isDeleted = true;
        this.updatedAt = updatedAt;
    }

    public void validateAuthor(MemberId memberId) {
        if (!memberId.equals(authorId)) {
            throw new IllegalArgumentException("피드 컬렉션 작성자가 아닙니다.");
        }
    }

    public void addFeed(FeedId feedId, MemberId memberId, String thumbnailUrl, LocalDateTime now) {
        validateAuthor(memberId);
        feedIds.add(feedId);
        if (this.thumbnailUrl == null) {
            this.thumbnailUrl = thumbnailUrl;
        }
        this.updatedAt = now;
    }

    public void removeFeed(FeedId feedId, MemberId memberId, LocalDateTime now) {
        validateAuthor(memberId);
        feedIds.remove(feedId);
        this.updatedAt = now;
    }

    public void updateLikeCount(long likeCount) {
        this.likeCount = (int) likeCount;
    }

}
