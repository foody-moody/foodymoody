package com.foodymoody.be.menu.util;

import com.foodymoody.be.common.util.ids.MenuId;
import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequestMenu;
import com.foodymoody.be.menu.domain.Menu;

public class MenuMapper {

    private MenuMapper() {
        throw new AssertionError("인스턴스화 불가능");
    }

    public static Menu makeMenu(MenuId menuId, FeedRegisterRequestMenu menu) {
        return new Menu(menuId, menu.getName(), menu.getRating());
    }

}
