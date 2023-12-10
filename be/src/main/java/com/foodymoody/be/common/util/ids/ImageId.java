package com.foodymoody.be.common.util.ids;

import com.foodymoody.be.common.util.Constants;
import java.util.Objects;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ImageId extends BaseId {

    public static final ImageId MEMBER_PROFILE_DEFAULT = IdFactory.createImageId(Constants.MEMBER_PROFILE_DEFAULT_IMAGE_ID);

    public ImageId(String value) {
        super(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImageId)) {
            return false;
        }
        ImageId imageId = (ImageId) o;
        return Objects.equals(getValue(), imageId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
