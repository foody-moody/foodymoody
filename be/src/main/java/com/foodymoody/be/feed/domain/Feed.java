package com.foodymoody.be.feed.domain;

import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feed {

    @Id
    private String id;
    private String location;
    // TODO: createdAt, updatedAt 추가 -> 테스트 코드 로직도 변경
    private String review;
    private String storeMood;
    private int likeCount;
    private boolean isLiked;
    private int commentCount;

    @Embedded
    private Images images;
    @Embedded
    private Menus menus;

    public Feed() {
    }

    public Feed(Images images, Menus menus) {
        this.images = images;
        this.menus = menus;
    }

    public Feed(String location, String review, String storeMood, List<Image> images, List<Menu> menus) {
        this.location = location;
        this.review = review;
        this.storeMood = storeMood;
        this.images = new Images(images);
        this.menus = new Menus(menus);
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

    public String getStoreMood() {
        return storeMood;
    }

    public List<Image> getImages() {
        return images.getNewUnmodifiedImages();
    }

    public List<Menu> getMenus() {
        return menus.getNewUnmodifiedMenus();
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

    public void update(String location, String review, String storeMood, List<Image> newImages, List<Menu> newMenus) {
        this.location = location;
        this.review = review;
        this.storeMood = storeMood;
        this.images.replaceWith(newImages);
        this.menus.replaceWith(newMenus);
    }

}
