package com.foodymoody.be.feed.util.validator;

import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.util.List;

public class FeedValidator {

    private FeedValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateFeedOfImagesAndMenus(List<Image> feedImages, List<Menu> feedMenus) {
        if (feedImages.size() != feedMenus.size()) {
            throw new IllegalArgumentException("피드의 이미지와 메뉴의 개수가 다릅니다.");
        }
    }

}
