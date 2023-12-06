package com.foodymoody.be.feed.dto.request;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedServiceDeleteRequest {

    private String id;
    private String memberId;

    public FeedServiceDeleteRequest(String id, String memberId) {
        this.id = id;
        this.memberId = memberId;
    }

    public String getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

}
