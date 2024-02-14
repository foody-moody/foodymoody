package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import lombok.Getter;

@Getter
public class MyCollectionWithFeedInclusionStatusResponse {

    private FeedCollectionId id;
    private String title;
    private boolean containsFeed;

    public MyCollectionWithFeedInclusionStatusResponse(FeedCollectionId id, String title, boolean containsFeed) {
        this.id = id;
        this.title = title;
        this.containsFeed = containsFeed;
    }

    public static MyCollectionWithFeedInclusionStatusResponse of(FeedCollectionId id, String title, boolean containsFeed) {
        return new MyCollectionWithFeedInclusionStatusResponse(id, title, containsFeed);
    }
}
