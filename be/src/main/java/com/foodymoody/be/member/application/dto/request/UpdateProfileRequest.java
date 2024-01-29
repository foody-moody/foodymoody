package com.foodymoody.be.member.application.dto.request;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import lombok.Getter;

@Getter
public class UpdateProfileRequest {

    private String nickname;
    private TasteMoodId tasteMoodId;
    private ImageId profileImageId;

}
