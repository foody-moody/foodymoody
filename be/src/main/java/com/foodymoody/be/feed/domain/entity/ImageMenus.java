package com.foodymoody.be.feed.domain.entity;

import com.foodymoody.be.feed.application.ImageMenuMapper;
import com.foodymoody.be.image.domain.Image;
import com.foodymoody.be.menu.domain.Menu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 일급 컬렉션
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageMenus {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageMenu> imageMenusList = new ArrayList<>();

    public ImageMenus(List<Image> newImages, List<Menu> newMenus) {
        this.imageMenusList.addAll(ImageMenuMapper.toImageMenu(newImages, newMenus));
    }

    public void replaceWith(List<Image> newImages, List<Menu> newMenus) {
        imageMenusList.clear();
        imageMenusList.addAll(ImageMenuMapper.toImageMenu(newImages, newMenus));
    }

    /**
     * 밖에서 참조하지 못하도록 새로운 변경 불가능한 리스트로 만든 후 리턴
     */
    public List<ImageMenu> getNewUnmodifiedImageMenus() {
        return Collections.unmodifiableList(imageMenusList);
    }

}
