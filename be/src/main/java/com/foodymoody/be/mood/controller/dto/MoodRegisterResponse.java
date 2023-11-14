package com.foodymoody.be.mood.controller.dto;

public class MoodRegisterResponse {

    private String id;
    private String name;

    public MoodRegisterResponse(String id, String name) {
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
