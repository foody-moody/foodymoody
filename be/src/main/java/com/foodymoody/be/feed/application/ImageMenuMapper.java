package com.foodymoody.be.feed.application;

import static com.foodymoody.be.feed.application.FeedValidator.validateFeedOfImagesAndMenus;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.domain.entity.ImageMenu;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.util.ArrayList;
import java.util.List;

public class ImageMenuMapper {

    private ImageMenuMapper() {
        throw new IllegalArgumentException("인스턴스화 불가능");
    }

    public static List<ImageMenu> toImageMenu(List<Image> newImages, List<Menu> newMenus) {
        validateFeedOfImagesAndMenus(newImages, newMenus);

        List<ImageMenu> imageMenus = new ArrayList<>();
        for (int i = 0; i < newImages.size(); i++) {
            Image image = newImages.get(i);
            Menu menu = newMenus.get(i);
            imageMenus.add(new ImageMenu(IdGenerator.generate(), image.getId().getValue(), menu.getId().getValue(), i));
        }

        return imageMenus;
    }

}
