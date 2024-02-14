package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import lombok.Getter;

@Getter
public class MyFeedCollectionTitleResponse {

    private FeedCollectionId id;
    private String title;
    private boolean isPrivate;

    public MyFeedCollectionTitleResponse(FeedCollectionId id, String title, boolean isPrivate) {
        this.id = id;
        this.title = title;
        this.isPrivate = isPrivate;
    }

}
