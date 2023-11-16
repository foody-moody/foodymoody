package com.foodymoody.be.mood.controller.dto;

public class MoodResponse {

    private String id;
    private String name;

    public MoodResponse(String id, String name) {
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
