package com.foodymoody.be.docs.member;

public enum MemberFixture {
    회원_알버트(1L, "albert@albert.com", "testtest123!", "알버트", "베지테리안", "https://www.image.com"),
    회원_설리(2L, "sully@sully.com", "testtest123!", "설리", "베지테리안", "https://www.image.com"),
    회원_보노(3L, "bono@bono.com", "testtest123!", "보노", "베지테리안", "https://www.image.com");

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String mood;
    private String myImageUrl;

    MemberFixture(Long id, String email, String password, String nickname, String mood, String myImageUrl) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.mood = mood;
        this.myImageUrl = myImageUrl;
    }

    public Long getId() {
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
