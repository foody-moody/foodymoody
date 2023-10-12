package com.foodymoody.be.feed.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class FeedRegisterRequest {

    private String review;
    private List<String> images;
    private List<FeedRegisterRequestMenu> menus;
}
