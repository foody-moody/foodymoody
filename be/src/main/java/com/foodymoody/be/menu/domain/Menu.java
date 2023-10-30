package com.foodymoody.be.menu.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Menu {

    @Id
    private String id;
    private String name;
    private int rating;

    public Menu(String id, String name, int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    public Menu() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

}
