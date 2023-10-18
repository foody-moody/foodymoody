package com.foodymoody.be.menu.util;

import com.foodymoody.be.feed.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.menu.domain.Menu;
import java.util.List;
import java.util.stream.Collectors;

public class MenuMapper {

    public static List<Menu> toOnlyMenu(List<FeedRegisterRequestMenu> menus) {
        return menus.stream()
                .map(m -> new Menu(m.getName(), m.getNumStar()))
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<Menu> toMenu(List<ImageMenuPair> imageMenuPairs) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> new Menu(imageMenuPair.getMenu().getName(), imageMenuPair.getMenu().getNumStar()))
                .collect(Collectors.toUnmodifiableList());
    }

}
