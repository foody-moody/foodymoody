package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.ImageMenuId;
import com.foodymoody.be.common.util.ids.MenuId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageMenu {

    @Id
    @Getter
    private ImageMenuId id;

    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "image_id"))
    private ImageId imageId;

    @Getter
    @AttributeOverride(name = "value", column = @Column(name = "menu_id"))
    private MenuId menuId;

    @Getter
    private int displayOrder;

    public ImageMenu(
            ImageMenuId id,
            ImageId imageId,
            MenuId menuId,
            int displayOrder
    ) {
        this.id = id;
        this.imageId = imageId;
        this.menuId = menuId;
        this.displayOrder = displayOrder;
    }

}
