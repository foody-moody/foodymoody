package com.foodymoody.be.feed.application.dto.request;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedServiceDeleteRequest {

    private FeedId id;
    private MemberId memberId;

    public FeedServiceDeleteRequest(FeedId id, MemberId memberId) {
        this.id = id;
        this.memberId = memberId;
    }

}
