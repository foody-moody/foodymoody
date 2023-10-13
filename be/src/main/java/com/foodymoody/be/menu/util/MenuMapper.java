package com.foodymoody.be.menu.util;

import com.foodymoody.be.feed.dto.FeedRegisterRequestMenu;
import com.foodymoody.be.menu.domain.Menu;
import java.util.List;
import java.util.stream.Collectors;

public class MenuMapper {

    public static List<Menu> toMenu(List<FeedRegisterRequestMenu> menus) {
        return menus.stream()
                .map(m -> new Menu(m.getName(), m.getNumStar()))
                .collect(Collectors.toUnmodifiableList());
    }

}
