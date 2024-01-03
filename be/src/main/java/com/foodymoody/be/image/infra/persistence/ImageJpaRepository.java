package com.foodymoody.be.image.infra.persistence;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.domain.ImageRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ImageJpaRepository extends ImageRepository, JpaRepository<Image, ImageId> {

    @Override
    @Modifying
    @Query("UPDATE Image i "
            + "SET i.deleted = true "
            + "WHERE i IN :images")
    void setDeletedTrueInBatch(List<Image> images);
}
