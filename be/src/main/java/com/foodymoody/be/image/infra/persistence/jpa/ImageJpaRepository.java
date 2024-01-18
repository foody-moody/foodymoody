package com.foodymoody.be.image.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.image.domain.Image;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ImageJpaRepository extends JpaRepository<Image, ImageId> {
    
    @Modifying
    @Query("UPDATE Image i "
            + "SET i.deleted = true "
            + "WHERE i IN :images")
    void setDeletedTrueInBatch(List<Image> images);

    Optional<Image> findByIdAndDeletedFalse(ImageId id);

    List<Image> findAllByDeletedTrue();

    List<Image> findAllByIdInAndDeletedFalse(List<ImageId> ids);
}
