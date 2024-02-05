package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberProfileImageResponse {

    private ImageId id;
    private String url;
}
