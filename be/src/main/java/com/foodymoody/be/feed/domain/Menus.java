package com.foodymoody.be.feed.domain;

import com.foodymoody.be.menu.domain.Menu;
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
public class Menus {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    public Menus(List<Menu> menus) {
        this.menus.addAll(menus);
    }

    public void replaceWith(List<Menu> newMenus) {
        menus.clear();
        menus.addAll(newMenus);
    }

    /**
     * 밖에서 참조하지 못하도록 새로운 변경 불가능한 리스트로 만든 후 리턴
     */
    public List<Menu> getNewUnmodifiedMenus() {
        return Collections.unmodifiableList(menus);
    }

}
