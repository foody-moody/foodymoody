package com.foodymoody.be.mood.controller.dto;

import java.util.List;

public class RandomMoodResponse {

    List<MoodResponse> content;

    public RandomMoodResponse(List<MoodResponse> content) {
        this.content = content;
    }

    public List<MoodResponse> getContent() {
        return content;
    }
}
