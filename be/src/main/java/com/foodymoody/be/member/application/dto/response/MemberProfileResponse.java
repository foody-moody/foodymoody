package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberProfileResponse {

    private MemberId id;
    private ImageId profileImageId;
    private String profileImageUrl;
    private String nickname;
    private String email;
    private TasteMoodId tasteMoodId;
    private long followingCount;
    private long followerCount;
    private boolean following;
    private boolean followed;
    private long feedCount;

}