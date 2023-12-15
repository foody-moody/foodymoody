package com.foodymoody.be.member.repository;

import com.foodymoody.be.common.util.ids.MemberId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FollowInfoMember {

    private MemberId id;
    private String nickname;
    private String profileImageUrl;

}
