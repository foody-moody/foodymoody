package com.foodymoody.be.store.domain;

import com.foodymoody.be.common.util.ids.StoreId;
import java.time.LocalDateTime;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @EmbeddedId
    private StoreId id;
    @Embedded
    private LocalDataKey localDataKey;
    @Getter
    private String name;
    private String roadAddress;
    private String address;
    private String phone;
    private Double x;
    private Double y;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    private StoreStatus status;
    private LocalDateTime updatedAt;

}
