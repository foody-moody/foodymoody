package com.foodymoody.be.feed.domain.repository.dto;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.Getter;

@Getter
public class MemberProfileFeedPreviewResponse {

    private FeedId id;
    private String imageUrl;

    public MemberProfileFeedPreviewResponse(FeedId id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }
}
