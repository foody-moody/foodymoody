package com.foodymoody.be.store_like_count.domain;

import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.StoreId;
import com.foodymoody.be.common.util.ids.StoreLikeCountId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreLikeCount {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "store_id"))
    private StoreId storeId;
    private Count count;
    @Version
    private Long version;

    public StoreLikeCount(StoreId storeId) {
        this.storeId = storeId;
        this.count = Count.create();
    }

    public static StoreLikeCount from(StoreId storeId) {
        return new StoreLikeCount(storeId);
    }

    public void increment() {
        long old = this.count.getCount();
        this.count = Count.from(++old);
    }

    public void decrement() {
        long old = this.count.getCount();
        this.count = Count.from(--old);
    }

    public long getCount() {
        return this.count.getCount();
    }

}


