package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class MyFeedCollectionAuthorResponse {

    private MemberId id;
    private String profileImageUrl;

    public MyFeedCollectionAuthorResponse(MemberId id, String profileImageUrl) {
        this.id = id;
        this.profileImageUrl = profileImageUrl;
    }

}
