package com.foodymoody.be.feed_collection.application.usecase.dto;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedCollectionAddFeedRequest {

    private FeedId feedId;

}
