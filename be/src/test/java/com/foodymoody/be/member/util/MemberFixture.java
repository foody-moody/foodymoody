package com.foodymoody.be.member.util;

public enum MemberFixture {
    회원_알버트("albert@albert.com", "testtest123!", "알버트", "무드1", "https://www.image.com"),
    회원_설리("sully@sully.com", "testtest123!", "설리", "무드1", "https://www.image.com"),
    회원_보노("bono@bono.com", "testtest123!", "보노", "무드1", "https://www.image.com");

    private String id;
    private String email;
    private String password;
    private String nickname;
    private String mood;
    private String myImageUrl;

    MemberFixture(String email, String password, String nickname, String mood, String myImageUrl) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.mood = mood;
        this.myImageUrl = myImageUrl;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMood() {
        return mood;
    }

    public String getMyImageUrl() {
        return myImageUrl;
    }
}
