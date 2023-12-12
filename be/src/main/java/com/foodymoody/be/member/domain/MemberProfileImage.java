package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.exception.ErrorMessage;
import com.foodymoody.be.common.util.ids.ImageId;
import io.jsonwebtoken.lang.Assert;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfileImage {

    public static final MemberProfileImage DEFAULT = new MemberProfileImage(ImageId.MEMBER_PROFILE_DEFAULT);

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "profile_image_id"))
    private ImageId imageId;

    public MemberProfileImage(ImageId imageId) {
        Assert.notNull(imageId, ErrorMessage.INVALID_IMAGE_ID.getMessage());
        this.imageId = imageId;
    }

    public ImageId getImageId() {
        return imageId;
    }
}
