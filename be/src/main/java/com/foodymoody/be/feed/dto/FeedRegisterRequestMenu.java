package com.foodymoody.be.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedRegisterRequestMenu {

    private String name;
    private int numStar;
}
