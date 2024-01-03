package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedServiceDeleteRequest {

    private FeedId id;
    private MemberId memberId;

    public FeedServiceDeleteRequest(FeedId id, MemberId memberId) {
        this.id = id;
        this.memberId = memberId;
    }

    public FeedId getId() {
        return id;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setId(FeedId id) {
        this.id = id;
    }

    public void setMemberId(MemberId memberId) {
        this.memberId = memberId;
    }

}
