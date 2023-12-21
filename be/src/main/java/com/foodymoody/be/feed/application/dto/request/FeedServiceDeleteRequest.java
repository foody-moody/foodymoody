package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedServiceDeleteRequest {

    private FeedId id;
    private String memberId;

    public FeedServiceDeleteRequest(FeedId id, String memberId) {
        this.id = id;
        this.memberId = memberId;
    }

    public FeedId getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setId(FeedId id) {
        this.id = id;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

}
