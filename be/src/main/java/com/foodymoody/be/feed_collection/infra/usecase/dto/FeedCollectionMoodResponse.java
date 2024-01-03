package com.foodymoody.be.feed_collection.infra.usecase.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import lombok.Getter;

@Getter
public class FeedCollectionMoodResponse {

    private FeedCollectionMoodId id;
    private String name;

    public FeedCollectionMoodResponse(FeedCollectionMoodId id, String name) {
        this.id = id;
        this.name = name;
    }
}
