package com.foodymoody.be.member.util;

public enum MemberFixture {
    비회원_알버트(null, "albert@albert.com", "testtest123!", "알버트", "1", "https://www.image.com"),
    비회원_설리(null, "sully@sully.com", "testtest123!", "설리", "1", "https://www.image.com"),
    비회원_보노(null, "bono@bono.com", "testtest123!", "보노", "1", "https://www.image.com"),
    회원_아티("1", "ati@ati.com", "ati123!", "아티", "1", null),
    회원_푸반("2", "puban@puban.com", "puban123!", "푸반", "1", null);

    private String id;
    private String email;
    private String password;
    private String nickname;
    private String tasteMoodId;
    private String myImageUrl;

    MemberFixture(String id, String email, String password, String nickname, String tasteMoodId, String myImageUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.tasteMoodId = tasteMoodId;
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

    public String getTasteMoodId() {
        return tasteMoodId;
    }

    public String getMyImageUrl() {
        return myImageUrl;
    }
}
