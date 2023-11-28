package com.foodymoody.be.heart.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HeartServiceRequest {

    private String feedId;
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public String getFeedId() {
        return feedId;
    }

}
