package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import lombok.Getter;

@Getter
public class MyFeedCollectionMoodResponse {

    private FeedCollectionMoodId id;
    private String name;
    private FeedCollectionId feedCollectionId;

    public MyFeedCollectionMoodResponse(FeedCollectionMoodId id, String name, FeedCollectionId feedCollectionId) {
        this.id = id;
        this.name = name;
        this.feedCollectionId = feedCollectionId;
    }
}
