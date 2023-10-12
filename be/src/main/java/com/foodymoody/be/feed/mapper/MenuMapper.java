package com.foodymoody.be.feed.mapper;

import com.foodymoody.be.feed.dto.FeedRegisterRequestMenu;
import com.foodymoody.be.feed.entity.Menu;
import java.util.List;
import java.util.stream.Collectors;

public class MenuMapper {


    public static List<Menu> toMenu(List<FeedRegisterRequestMenu> menus) {
        return menus.stream()
                .map(m -> new Menu(m.getName(), m.getNumStar()))
                .collect(Collectors.toUnmodifiableList());
    }

}
