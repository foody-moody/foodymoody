package com.foodymoody.be.comment.application.dto.response;

public class MemberSummaryResponse {

    private String id;
    private String nickname;
    private String imageUrl;

    public MemberSummaryResponse(String id, String nickname, String imageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
