package com.foodymoody.be.image.domain;

import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Image {

    @Id
    private ImageId id;
    private String url;
    @CreatedDate
    private LocalDateTime createdAt;
    @AttributeOverride(name = "value", column = @Column(name = "uploader_id"))
    private MemberId uploaderId;

    public Image(ImageId id, String url, MemberId uploaderId) {
        this.id = id;
        this.url = url;
        this.uploaderId = uploaderId;
    }

    public void validateIsUploader(MemberId memberId) {
        if (!uploaderId.isSame(memberId)) {
            throw new UnauthorizedException();
        }
    }
}
