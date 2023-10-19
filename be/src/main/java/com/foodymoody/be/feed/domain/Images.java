package com.foodymoody.be.feed.domain;

import com.foodymoody.be.image.domain.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

/**
 * 일급 컬렉션
 */
@Embeddable
public class Images {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> newImages = new ArrayList<>();

    public Images() {
    }

    public Images(List<Image> newImages) {
        this.newImages = newImages;
    }

    public void clearImages() {
        newImages.clear();
    }

    public void addAllImages(List<Image> images) {
        newImages.addAll(images);
    }

    /**
     * 밖에서 참조하지 못하도록 새로운 변경 불가능한 리스트로 만든 후 리턴
     */
    public List<Image> getNewUnmodifiedImages() {
        return Collections.unmodifiableList(newImages);
    }
}
