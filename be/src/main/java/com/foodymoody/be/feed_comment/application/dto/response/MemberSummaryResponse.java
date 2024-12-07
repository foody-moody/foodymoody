package com.foodymoody.be.feed_comment.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class MemberSummaryResponse {

    private final MemberId id;
    private final String nickname;
    private final String imageUrl;

    public MemberSummaryResponse(MemberId id, String nickname, String imageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }

}
