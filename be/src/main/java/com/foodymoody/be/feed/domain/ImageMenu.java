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
    private String menuId;
    private int displayOrder;

    public ImageMenu(String id, String imageId, String menuId, int displayOrder) {
        this.id = id;
        this.imageId = imageId;
        this.menuId = menuId;
        this.displayOrder = displayOrder;
    }

    public String getId() {
        return id;
    }

    public String getImageId() {
        return imageId;
    }

    public String getMenuId() {
        return menuId;
    }

}
