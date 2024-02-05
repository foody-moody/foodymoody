package com.foodymoody.be.menu.util;

import com.foodymoody.be.feed.application.dto.request.FeedRegisterRequestMenu;

public class MenuFixture {

    public static FeedRegisterRequestMenu getFeedRegisterRequestMenuWithRating(int rating) {
        return FeedRegisterRequestMenu.builder()
                .name("메뉴")
                .rating(rating)
                .build();
    }

}
