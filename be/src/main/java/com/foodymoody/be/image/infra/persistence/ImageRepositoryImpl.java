package com.foodymoody.be.image.infra.persistence;

import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.image.domain.ImageRepository;
import com.foodymoody.be.image.infra.persistence.jpa.ImageJpaRepository;
import com.foodymoody.be.member.domain.Member;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {

    private final ImageJpaRepository jpaRepository;

    @Override
    public Optional<Image> fetchImageDefaultProfile(ImageId defaultId) {
        return jpaRepository.findById(defaultId);
    }

    @Override
    public Image save(Image image) {
        return jpaRepository.save(image);
    }

    @Override
    public void delete(Image image) {
        jpaRepository.delete(image);
    }

    @Override
    public Optional<Image> findByIdAndDeletedFalse(ImageId id) {
        return jpaRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public List<Image> findAllByDeletedTrue() {
        return jpaRepository.findAllByDeletedTrue();
    }

    @Override
    public void deleteAllInBatch(Iterable<Image> images) {
        jpaRepository.deleteAllInBatch(images);
    }

    @Override
    public void setDeletedTrueInBatch(List<Image> images) {
        jpaRepository.setDeletedTrueInBatch(images);
    }

    @Override
    public void setDeletedTrueAll(Member member) {
        jpaRepository.setDeletedTrueAll(member.getId());
    }

    @Override
    public List<Image> findAllByIdInAndDeletedFalse(List<ImageId> ids) {
        return jpaRepository.findAllByIdInAndDeletedFalse(ids);
    }

}
