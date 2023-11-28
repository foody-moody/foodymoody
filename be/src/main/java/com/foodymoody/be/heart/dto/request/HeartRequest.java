package com.foodymoody.be.heart.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRequest {

    private String feedId;

    public String getFeedId() {
        return feedId;
    }

}
