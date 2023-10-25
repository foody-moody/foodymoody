package com.foodymoody.be.menu.util;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.feed.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.dto.request.ImageMenuPair;
import com.foodymoody.be.menu.domain.Menu;
import java.util.List;
import java.util.stream.Collectors;

public class MenuMapper {

    private MenuMapper() {
        throw new AssertionError("인스턴스화 불가능");
    }

    public static List<Menu> toOnlyMenu(List<FeedRegisterRequestMenu> menus) {
        return menus.stream()
                .map(menu -> getMenu(IdGenerator.generate(), menu))
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<Menu> toMenu(List<ImageMenuPair> imageMenuPairs) {
        return imageMenuPairs.stream()
                .map(imageMenuPair -> getMenu(IdGenerator.generate(), imageMenuPair.getMenu()))
                .collect(Collectors.toUnmodifiableList());
    }

    public static Menu getMenu(String generatedId, FeedRegisterRequestMenu menu) {
        return new Menu(generatedId, menu.getName(), menu.getRating());
    }

}
