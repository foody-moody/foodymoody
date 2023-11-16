package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.BaseEntity;
import com.foodymoody.be.common.WrappedId;
import com.foodymoody.be.common.exception.IncorrectMemberPasswordException;
import com.foodymoody.be.common.exception.InvalidReconfirmPasswordException;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {

    private String email;
    private String nickname;
    private String password;
    private String profileImageId;
    private String moodId;

    private Member(WrappedId id, String email, String nickname, String password, String moodId) {
        this.wrappedId = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.moodId = moodId;
    }

    public static Member of(String id, String email, String nickname, String password, String reconfirmPassword, String moodId) {
        if (!Objects.equals(reconfirmPassword, password)) {
            throw new InvalidReconfirmPasswordException();
        }
        return new Member(new WrappedId(id), email, nickname, password, moodId);
    }

    public String getId() {
        return wrappedId.getId();
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

    public String getMoodId() {
        return moodId;
    }

    public void validatePassword(String password) {
        if (Objects.isNull(password) || !Objects.equals(password, this.password)) {
            throw new IncorrectMemberPasswordException();
        }
    }

    //    TODO 프로필 이미지 기능 구현
}
