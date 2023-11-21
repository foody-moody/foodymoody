package com.foodymoody.be.comment.controller.dto;

public class MemberResponse {

    private String id;
    private String imageUrl;
    private String nickname;

    public MemberResponse(String id, String imageUrl, String nickname) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
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
}
