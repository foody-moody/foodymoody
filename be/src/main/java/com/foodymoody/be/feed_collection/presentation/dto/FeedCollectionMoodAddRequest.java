package com.foodymoody.be.feed_collection.presentation.dto;

import com.foodymoody.be.common.util.ids.FeedCollectionMoodId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCollectionMoodAddRequest {

    private FeedCollectionMoodId moodId;
}
