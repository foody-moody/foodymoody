package com.foodymoody.be.store.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.Getter;

@Getter
public class StoreFeedPreviewResponse {

    private FeedId feedId;
    private String thumbnailUrl;

    public StoreFeedPreviewResponse(FeedId feedId, String thumbnailUrl) {
        this.feedId = feedId;
        this.thumbnailUrl = thumbnailUrl;
    }
}
