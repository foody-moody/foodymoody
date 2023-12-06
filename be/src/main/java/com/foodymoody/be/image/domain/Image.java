package com.foodymoody.be.image.domain;

import com.foodymoody.be.common.exception.UnauthorizedException;
import java.time.LocalDateTime;
import java.util.Objects;
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
    private String id;
    private String url;
    @CreatedDate
    private LocalDateTime createdAt;
    private String uploaderId;

    public Image(String id, String url, String uploaderId) {
        this.id = id;
        this.url = url;
        this.uploaderId = uploaderId;
    }

    public void validateIsUploader(String memberId) {
        if (!Objects.equals(memberId, uploaderId)) {
            throw new UnauthorizedException();
        }
    }
}
