package com.foodymoody.be.menu.domain;

import com.foodymoody.be.common.util.ids.MenuId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menu {

    @Id
    private MenuId id;
    private String name;
    private int rating;

    public Menu(MenuId id, String name, int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Menu() {

    }

    public MenuId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

}
