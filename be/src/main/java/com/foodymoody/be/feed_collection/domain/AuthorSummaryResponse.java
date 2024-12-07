package com.foodymoody.be.feed_collection.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class AuthorSummaryResponse {

    private MemberId id;
    private String name;
    private String mood;
    private String profileImageUrl;

    public AuthorSummaryResponse(
            MemberId id,
            String name,
            String mood,
            String profileImageUrl
    ) {
        this.id = id;
        this.name = name;
        this.mood = mood;
        this.profileImageUrl = profileImageUrl;
    }

}
