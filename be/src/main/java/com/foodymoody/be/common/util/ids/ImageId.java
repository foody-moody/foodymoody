package com.foodymoody.be.common.util.ids;

import com.foodymoody.be.common.util.Constants;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ImageId extends BaseId {

    public static final ImageId MEMBER_PROFILE_DEFAULT = IdFactory.createImageId(Constants.DEFAULT_MEMBER_PROFILE_IMAGE_ID);

    public ImageId(String value) {
        super(value);
    }
}
