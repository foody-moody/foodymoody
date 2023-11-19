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

    public static Menu makeMenu(String generatedId, FeedRegisterRequestMenu menu) {
        return new Menu(generatedId, menu.getName(), menu.getRating());
    }

}
