package com.foodymoody.be.feed.domain;

import com.foodymoody.be.image.domain.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 일급 컬렉션
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Images {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public Images(List<Image> newImages) {
        this.images.addAll(newImages);
    }

    public void replaceWith(List<Image> newImages) {
        images.clear();
        images.addAll(newImages);
    }

    /**
     * 밖에서 참조하지 못하도록 새로운 변경 불가능한 리스트로 만든 후 리턴
     */
    public List<Image> getNewUnmodifiedImages() {
        return Collections.unmodifiableList(images);
    }

}
