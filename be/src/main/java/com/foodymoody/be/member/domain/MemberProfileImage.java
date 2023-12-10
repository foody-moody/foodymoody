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

    public static final String DEFAULT_MEMBER_PROFILE_IMAGE_URL
            = "https://foodymoody-test.s3.ap-northeast-2.amazonaws.com/foodymoody_logo.png";
    public static final MemberProfileImage DEFAULT =
            new MemberProfileImage(ImageId.MEMBER_PROFILE_DEFAULT, DEFAULT_MEMBER_PROFILE_IMAGE_URL);

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "profile_image_id"))
    private ImageId id;
    @Column(name = "profile_image_url")
    private String url;

    public MemberProfileImage(ImageId profileImageId, String url) {
        if (Objects.isNull(profileImageId) || Objects.isNull(url)) {
            throw new IllegalArgumentException("profileImageId와 profileImageUrl이 null이어서는 안됩니다");
        }
        this.id = profileImageId;
        this.url = url;
    }

    public ImageId getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
