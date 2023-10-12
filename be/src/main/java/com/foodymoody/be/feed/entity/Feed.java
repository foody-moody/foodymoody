package com.foodymoody.be.feed.entity;

import java.util.List;

public class Feed {

    private Long id;
    private String review;
    private List<String> images;
    private List<Menu> menus;

    public Feed(String review, List<String> images, List<Menu> menus) {
        this.review = review;
        this.images = images;
        this.menus = menus;
    }
}
