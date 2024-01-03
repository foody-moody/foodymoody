package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.ImageId;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfileImage {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "profile_image_id"))
    private ImageId id;

    public MemberProfileImage(ImageId profileImageId) {
        if (Objects.isNull(profileImageId)) {
            throw new IllegalArgumentException("profileImageId가 null일 수 없습니다");
        }
        this.id = profileImageId;
    }

    public ImageId getId() {
        return id;
    }

}
