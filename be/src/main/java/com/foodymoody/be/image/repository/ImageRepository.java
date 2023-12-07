package com.foodymoody.be.image.repository;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, ImageId> {

}
