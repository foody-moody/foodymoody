package com.foodymoody.be.image.domain;

import com.foodymoody.be.common.util.ids.ImageId;
import java.util.Optional;

public interface ImageRepository {

    Image save(Image image);

    void delete(Image image);

    Optional<Image> findById(ImageId id);

}
