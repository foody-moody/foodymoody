package com.foodymoody.be.feed.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageMenu {

    @Id
    private String id;
    private String imageId;
    private String menuName;
    private int rating;

    public ImageMenu(String id, String imageId, String menuName, int rating) {
        this.id = id;
        this.imageId = imageId;
        this.menuName = menuName;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getImageId() {
        return imageId;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getRating() {
        return rating;
    }

}
