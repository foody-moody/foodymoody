package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class AuthorSummaryResponse {

    private MemberId authorId;
    private String authorName;
    private String authorMood;
    private String authorProfileImageUrl;

    public AuthorSummaryResponse(
            MemberId authorId, String authorName, String authorMood, String authorProfileImageUrl
    ) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorMood = authorMood;
        this.authorProfileImageUrl = authorProfileImageUrl;
    }
}
