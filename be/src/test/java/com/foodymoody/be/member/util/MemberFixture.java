package com.foodymoody.be.member.util;

public enum MemberFixture {
    회원_알버트(null,"albert@albert.com", "testtest123!", "알버트", "베지테리언", "https://www.image.com"),
    회원_설리(null,"sully@sully.com", "testtest123!", "설리", "베지테리언", "https://www.image.com"),
    회원_보노(null,"bono@bono.com", "testtest123!", "보노", "베지테리언", "https://www.image.com"),
    회원_아티("1", "ati@ati.com", "ati123!", "아티", "베지테리언", null),
    회원_푸반("2", "puban@puban.com", "puban123!", "푸반", "베지테리언", null);

    private String id;
    private String email;
    private String password;
    private String nickname;
    private String mood;
    private String myImageUrl;

    MemberFixture(String id, String email, String password, String nickname, String mood, String myImageUrl) {
        this.id = id;
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
