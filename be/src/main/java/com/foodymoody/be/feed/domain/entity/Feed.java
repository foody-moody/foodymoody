package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.common.event.EventManager;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.entity.Menu;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Feed {

    @Id
    @Getter
    private FeedId id;

    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    @Getter
    private MemberId memberId;

    @Getter
    private String profileImageUrl;

    @AttributeOverride(name = "value", column = @Column(name = "store_id"))
    @Getter
    private StoreId storeId;

    @Getter
    private LocalDateTime createdAt;

    @Getter
    private LocalDateTime updatedAt;

    @Getter
    private String review;

    @Getter
    private int likeCount;

    @Getter
    private int commentCount;

    @ManyToMany(fetch = FetchType.LAZY)
    @Getter
    private List<StoreMood> storeMoods;

    @Embedded
    private ImageMenus imageMenus;

    public Feed(
            FeedId id,
            MemberId memberId,
            StoreId storeId,
            String review,
            List<StoreMood> storeMoods,
            List<Image> images,
            List<Menu> menus,
            String profileImageUrl,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.memberId = memberId;
        this.storeId = storeId;
        this.review = review;
        this.storeMoods = storeMoods;
        this.imageMenus = new ImageMenus(images, menus);
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
        EventManager.raise(toEvent(id, memberId, profileImageUrl, createdAt));
    }

    public void update(
            MemberId memberId,
            StoreId newStoreId,
            String newReview,
            List<StoreMood> newStoreMoods,
            List<Image> newImages,
            List<Menu> newMenus,
            String profileImageUrl,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.memberId = memberId;
        this.storeId = newStoreId;
        this.review = newReview;
        this.storeMoods = newStoreMoods;
        this.imageMenus.replaceWith(newImages, newMenus);
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateLikeCountBy(int heartCount) {
        this.likeCount = heartCount;
    }

    public List<ImageMenu> getImageMenus() {
        return imageMenus.getNewUnmodifiedImageMenus();
    }

    private static FeedAddedEvent toEvent(
            FeedId id,
            MemberId memberId,
            String profileImageUrl,
            LocalDateTime createdAt
    ) {
        return FeedAddedEvent.of(
                memberId,
                id,
                profileImageUrl,
                createdAt
        );
    }

}
