package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.Getter;

@Getter
public class FeedPreviewResponse {

    private FeedId id;
    private String imageUrl;

    public FeedPreviewResponse(FeedId id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

}
