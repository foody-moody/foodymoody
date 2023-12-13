package com.foodymoody.be.feed_collection.infra.usecase;

import lombok.Getter;

@Getter
public class AuthorSummaryResponse {

    private String authorId;
    private String authorName;
    private String authorMood;
    private String authorProfileImageUrl;

    public AuthorSummaryResponse(String authorId, String authorName, String authorMood, String authorProfileImageUrl) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorMood = authorMood;
        this.authorProfileImageUrl = authorProfileImageUrl;
    }
}
