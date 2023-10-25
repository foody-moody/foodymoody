package com.foodymoody.be.menu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {

    @Id
    private String id;
    private String name;
    private int numStar;

    public Menu(String id, String name, int numStar) {
        this.id = id;
        this.name = name;
        this.numStar = numStar;
    }

    public Menu() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumStar() {
        return numStar;
    }

}
