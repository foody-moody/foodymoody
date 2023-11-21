package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.exception.IncorrectMemberPasswordException;
import com.foodymoody.be.common.exception.InvalidReconfirmPasswordException;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @EmbeddedId
    private MemberId id;
    private String email;
    private String nickname;
    private String password;
    private String profileImageId;
    private String tasteMoodId;

    private Member(MemberId id, String email, String nickname, String password, String moodId) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.tasteMoodId = moodId;
    }

    public static Member of(String id, String email, String nickname, String password, String reconfirmPassword, String moodId) {
        if (!Objects.equals(reconfirmPassword, password)) {
            throw new InvalidReconfirmPasswordException();
        }
        return new Member(new MemberId(id), email, nickname, password, moodId);
    }

    public MemberId getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImageId() {
        return profileImageId;
    }

    public String getTasteMoodId() {
        return tasteMoodId;
    }

    public void validatePassword(String password) {
        if (Objects.isNull(password) || !Objects.equals(password, this.password)) {
            throw new IncorrectMemberPasswordException();
        }
    }

    //    TODO 프로필 이미지 기능 구현
}
