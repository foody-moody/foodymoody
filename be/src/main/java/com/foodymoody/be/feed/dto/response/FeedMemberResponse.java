package com.foodymoody.be.feed.dto.response;

public class FeedMemberResponse {

    private String imageUrl;
    private String nickname;
    private String mood;

    public FeedMemberResponse(String imageUrl, String nickname, String mood) {
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.mood = mood;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMood() {
        return mood;
    }

}
