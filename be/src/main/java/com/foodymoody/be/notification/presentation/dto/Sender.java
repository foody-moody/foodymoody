package com.foodymoody.be.notification.presentation.dto;

import lombok.Getter;

@Getter
public class Sender {

    private String id;
    private String nickname;
    private String imageUrl;

    public Sender(String id, String nickname, String imageUrl) {
        this.id = id;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
    }
}
