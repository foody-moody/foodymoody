package com.foodymoody.be.common.util.ids;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode(callSuper = false)
public class ImageId extends BaseId {

    public static final ImageId MEMBER_PROFILE_DEFAULT = new ImageId("member-profile-default");

    public ImageId(String value) {
        super(value);
    }

    @Override
    @EqualsAndHashCode.Include
    public String getValue() {
        return super.getValue();
    }
}
