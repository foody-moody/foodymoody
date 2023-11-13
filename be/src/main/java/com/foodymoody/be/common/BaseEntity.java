package com.foodymoody.be.common;

import java.time.LocalDateTime;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;

@MappedSuperclass
public class BaseEntity {

    @EmbeddedId
    protected WrappedId id;

    @CreatedDate
    protected LocalDateTime createdAt;

}
