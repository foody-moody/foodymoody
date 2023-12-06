package com.foodymoody.be.feed.domain;

import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Feed {

    @Id
    private String id;
    private String memberId;
    // TODO
//    private String profileImageId;
    private String location;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String review;
    private int likeCount;
    private boolean isLiked;
    private int commentCount;

    @Embedded
    private ImageMenus imageMenus;
    @Embedded
    private StoreMoods storeMoods;

    public Feed() {
    }

    public Feed(String id, String memberId, String location, String review, List<String> moodIds, List<Image> images,
                List<Menu> menus) {
        this.id = id;
        this.memberId = memberId;
        this.location = location;
        this.review = review;
        this.storeMoods = new StoreMoods(moodIds);
        this.imageMenus = new ImageMenus(images, menus);
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getReview() {
        return review;
    }

    public List<String> getStoreMoodIds() {
        return storeMoods.getStoreMoodIds();
    }

    public List<ImageMenu> getImageMenus() {
        return imageMenus.getNewUnmodifiedImageMenus();
    }

    public int getLikeCount() {
        return likeCount;
    }

    public boolean isLiked() {
        return isLiked;
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

    public StoreMoods getStoreMood() {
        return storeMoods;
    }

    public String getMemberId() {
        return memberId;
    }

    public void update(String memberId, String newLocation, String newReview, List<String> newStoreMoodIds, List<Image> newImages,
                       List<Menu> newMenus) {
        this.memberId = memberId;
        this.location = newLocation;
        this.review = newReview;
        this.storeMoods = new StoreMoods(newStoreMoodIds);
        this.imageMenus.replaceWith(newImages, newMenus);
    }

    public void updateIsLikedBy(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public void updateLikeCountBy(int heartCount) {
        this.likeCount = heartCount;
    }

}
