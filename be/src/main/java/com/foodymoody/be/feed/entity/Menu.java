package com.foodymoody.be.feed.entity;

public class Menu {

    private Long id;
    private String name;
    private int numStar;


    public Menu(String name, int numStar) {
        this.name = name;
        this.numStar = numStar;
    }
}
