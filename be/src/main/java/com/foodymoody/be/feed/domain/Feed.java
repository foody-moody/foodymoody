package com.foodymoody.be.feed.domain;

import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Feed {

    @Id
    private String id;
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
    private StoreMood storeMood;

    public Feed() {
    }

    public Feed(String id, String location, String review, List<String> storeMood, List<Image> images,
            List<Menu> menus) {
        this.id = id;
        this.location = location;
        this.review = review;
        this.storeMood = new StoreMood(storeMood);
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
        return storeMood.getStoreMoodIds();
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

    public void update(String location, String review, List<String> storeMood, List<Image> newImages,
            List<Menu> newMenus) {
        this.location = location;
        this.review = review;
        this.storeMood = new StoreMood(storeMood);
        this.imageMenus.replaceWith(newImages, newMenus);
    }

}
