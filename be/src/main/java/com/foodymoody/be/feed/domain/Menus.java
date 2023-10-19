package com.foodymoody.be.feed.domain;

import com.foodymoody.be.menu.domain.Menu;
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
public class Menus {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    public Menus() {
    }

    public Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public void clearMenus() {
        menus.clear();
    }

    public void addAllMenus(List<Menu> newMenus) {
        menus.addAll(newMenus);
    }

    /**
     * 밖에서 참조하지 못하도록 새로운 변경 불가능한 리스트로 만든 후 리턴
     */
    public List<Menu> getNewUnmodifiedMenus() {
        return Collections.unmodifiableList(menus);
    }

}
