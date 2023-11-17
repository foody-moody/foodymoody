package com.foodymoody.be.feed.service.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImageIdNamePair {

    private final String id;
    private final String url;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}
