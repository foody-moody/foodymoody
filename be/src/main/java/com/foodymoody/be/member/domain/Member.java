package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.exception.InvalidReconfirmPasswordException;
import com.foodymoody.be.image.domain.Image;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    private String id;
    private String email;
    private String nickname;
    private String password;
    @OneToOne
    @JoinColumn(name = "profile_image")
    private Image profileImage;
    private String mood;

    private Member(String id, String email, String nickname, String password, String mood) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.mood = mood;
    }

    public static Member of(String id, String email, String nickname, String password, String reconfirmPassword, String mood) {
        if (!Objects.equals(reconfirmPassword, password)) {
            throw new InvalidReconfirmPasswordException();
        }
        return new Member(id, email, nickname, password, mood);
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getProfileImageUrl() {
        if (Objects.nonNull(this.profileImage)) {
            return this.profileImage.getUrl();
        }
        return null;
    }

    public String getMood() {
        return mood;
    }

    //    TODO 프로필 이미지 기능 구현
}
