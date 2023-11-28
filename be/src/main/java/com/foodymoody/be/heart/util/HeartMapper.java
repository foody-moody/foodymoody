package com.foodymoody.be.heart.util;

import com.foodymoody.be.common.util.IdGenerator;
import com.foodymoody.be.heart.domain.Heart;
import com.foodymoody.be.heart.dto.request.HeartRequest;
import com.foodymoody.be.heart.dto.request.HeartServiceRequest;
import com.foodymoody.be.heart.dto.response.HeartResponse;

public class HeartMapper {

    private HeartMapper() {
        throw new IllegalArgumentException("Utility Class");
    }

    public static HeartServiceRequest toHeartServiceRequest(HeartRequest heartRequest, String memberId) {
        return new HeartServiceRequest(heartRequest.getFeedId(), memberId);
    }

    public static Heart toHeartWithFeedIdAndMemberId(String feedId, String memberId) {
        return new Heart(IdGenerator.generate(), feedId, memberId);
    }

    public static HeartResponse toHeartResponse(Heart savedHeart) {
        return new HeartResponse(savedHeart.getId(), savedHeart.getFeedId(), savedHeart.getMemberId());
    }

}
