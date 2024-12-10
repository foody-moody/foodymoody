package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.image.application.dto.ImageDefaultProfileData;
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

    public static final ImageId defaultBasicProfileId = new ImageId("member-profile-default");

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "profile_image_id"))
    private ImageId id;

    @Column(name = "profile_image_url")
    private String url;

    protected MemberProfileImage(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }

    public static MemberProfileImage of(ImageId id, String url, ImageDefaultProfileData defaultData) {
        if (Objects.isNull(id) || Objects.isNull(url)) {
            return new MemberProfileImage(defaultData.getId(), defaultData.getProfileImageUrl());
        }

        return new MemberProfileImage(id, url);
    }

    public ImageId getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}
