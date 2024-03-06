package com.foodymoody.be.member.application.dto.response;

import com.foodymoody.be.common.util.ids.ImageId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfileImageResponse {

    private ImageId id;
    private String url;

    public static MemberProfileImageResponse of(ImageId id, String url) {
        return new MemberProfileImageResponse(id, url);
    }
}
