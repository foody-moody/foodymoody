package com.foodymoody.be.image.domain;

import com.foodymoody.be.common.exception.UnauthorizedException;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Image {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ImageId id;
    // TODO URL 로 리팩토링
    private String url;
    @CreatedDate
    private LocalDateTime createdAt;
    @AttributeOverride(name = "value", column = @Column(name = "uploader_id"))
    private MemberId uploaderId;
    private boolean deleted;

    public Image(ImageId id, String url, MemberId uploaderId) {
        this.id = id;
        this.url = url;
        this.uploaderId = uploaderId;
    }

    public void validateIsUploader(MemberId memberId) {
        if (!Objects.equals(memberId, uploaderId)) {
            throw new UnauthorizedException();
        }
    }

    public void softDelete(MemberId currentMemberId) {
        validateIsUploader(currentMemberId);
        deleted = Boolean.TRUE;
    }
}
