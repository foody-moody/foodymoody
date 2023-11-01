package com.foodymoody.be.mood.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mood {

    @Id
    private String id;
    private String name;

    public Mood() {
    }

    public Mood(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
