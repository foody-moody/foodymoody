package com.foodymoody.be.menu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int numStar;

    public Menu(String name, int numStar) {
        this.name = name;
        this.numStar = numStar;
    }

    public Menu() {

    }

    public String getName() {
        return name;
    }

    public int getNumStar() {
        return numStar;
    }

}
