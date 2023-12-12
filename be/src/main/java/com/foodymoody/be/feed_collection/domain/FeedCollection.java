package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCollection {

    @Id
    private FeedCollectionId id;
    @AttributeOverride(name = "value", column = @Column(name = "author_id"))
    private MemberId authorId;
    private String thumbnailUrl;
    private String title;
    private String description;
    private int heartCount;
    private int followerCount;
    private boolean isPrivate;
    private boolean isDeleted;
    @Embedded
    private FeedIds feedIds;
    @Embedded
    private CommentIds commentIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FeedCollection(
            FeedCollectionId id, MemberId memberId, String thumbnailUrl, String title, String description,
            int followerCount,
            boolean isPrivate, boolean isDeleted, List<FeedId> feedIds
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
        this.commentIds = new CommentIds();
    }
}
