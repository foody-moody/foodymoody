package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
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
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Feed {

    @Id
    private FeedId id;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private String profileImageUrl;
    private String location;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String review;
    private int likeCount;
    private int commentCount;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<StoreMood> storeMoods;

    @Embedded
    private ImageMenus imageMenus;

    public Feed(FeedId id, MemberId memberId, String location, String review, List<StoreMood> storeMoods,
                List<Image> images, List<Menu> menus, String profileImageUrl,
                LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.location = location;
        this.review = review;
        this.storeMoods = storeMoods;
        this.imageMenus = new ImageMenus(images, menus);
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
    }

    public FeedId getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getReview() {
        return review;
    }

    public List<StoreMood> getStoreMoods() {
        return storeMoods;
    }

    public List<ImageMenu> getImageMenus() {
        return imageMenus.getNewUnmodifiedImageMenus();
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void update(MemberId memberId, String newLocation, String newReview, List<StoreMood> newStoreMoods,
                       List<Image> newImages, List<Menu> newMenus, String profileImageUrl,
                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.memberId = memberId;
        this.location = newLocation;
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

}
