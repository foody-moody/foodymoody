package com.foodymoody.be.feed.dto.response;

public class FeedMemberResponse {

    private String id;
    private String imageUrl;
    private String nickname;
    private String tasteMood;

    public FeedMemberResponse(String id, String imageUrl, String nickname, String tasteMood) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.tasteMood = tasteMood;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTasteMood() {
        return tasteMood;
    }

}
