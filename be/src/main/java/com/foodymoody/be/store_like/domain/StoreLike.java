package com.foodymoody.be.store_like.domain;

import com.foodymoody.be.common.event.Event;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreLikeId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreLike {

    @Getter
    @EmbeddedId
    private StoreLikeId id;
    @AttributeOverride(name = "value", column = @Column(name = "store_id"))
    private StoreId storeId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    private LocalDateTime createdAt;

    public StoreLike(StoreLikeId id, StoreId storeId, MemberId memberId, LocalDateTime createdAt) {
        this.id = id;
        this.storeId = storeId;
        this.memberId = memberId;
        this.createdAt = createdAt;
    }

    public static StoreLike of(StoreLikeId id, StoreId storeId, MemberId memberId, LocalDateTime now) {
        return new StoreLike(id, storeId, memberId, now);
    }

    public StoreId getStoreId() {
        return this.storeId;
    }
}
