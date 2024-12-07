package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.IdFactory;
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

    public static final MemberProfileImage DEFAULT =
            MemberProfileImage.of(
                    IdFactory.createImageId("member-profile-default"),
                    "http://dummyimage.com/236x100.png/5fa2dd/ffffff");

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "profile_image_id"))
    private ImageId id;

    @Column(name = "profile_image_url")
    private String url;

    protected MemberProfileImage(ImageId id, String url) {
        this.id = id;
        this.url = url;
    }

    public static MemberProfileImage of(ImageId id, String url) {
        if (Objects.isNull(id) || Objects.isNull(url)) {
            return DEFAULT;
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
