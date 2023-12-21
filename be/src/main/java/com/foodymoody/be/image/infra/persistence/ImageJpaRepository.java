package com.foodymoody.be.image.infra.persistence;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.domain.ImageRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageJpaRepository extends ImageRepository, JpaRepository<Image, ImageId> {

}
