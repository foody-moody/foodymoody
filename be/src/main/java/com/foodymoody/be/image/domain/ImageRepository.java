package com.foodymoody.be.image.domain;

import com.foodymoody.be.common.util.ids.ImageId;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface ImageRepository {

    Optional<Image> fetchImageDefaultProfile(ImageId defaultId);

    Image save(Image image);

    void delete(Image image);

    Optional<Image> findByIdAndDeletedFalse(ImageId id);

    List<Image> findAllByDeletedTrue();

    void deleteAllInBatch(Iterable<Image> images);

    void setDeletedTrueInBatch(List<Image> images);

    List<Image> findAllByIdInAndDeletedFalse(List<ImageId> ids);

}
