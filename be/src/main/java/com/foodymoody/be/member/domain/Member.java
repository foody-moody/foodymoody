package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.exception.InvalidReconfirmPasswordException;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
    @Embedded
    private Password password;
    @Embedded
    private MemberProfileImage profileImage;
    @AttributeOverride(name = "value", column = @Column(name = "taste_mood_id"))
    private TasteMoodId tasteMoodId;

    private Member(MemberId id, String email, String nickname, String password, TasteMoodId moodId) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = new Password(password);
        this.tasteMoodId = moodId;
        this.profileImage = MemberProfileImage.DEFAULT;
        Events.publish(toMemberCreatedEvent());
    }

    public static Member of(String id, String email, String nickname, String password, String reconfirmPassword,
            TasteMoodId moodId) {
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

    public ImageId getProfileImageId() { return profileImage.getId(); }

    public String getProfileImageUrl() {
        return profileImage.getUrl();
    }

    public TasteMoodId getTasteMoodId() {
        return tasteMoodId;
    }

    public void checkPasswordMatch(String password) {
        this.password.validateEquals(password);
    }

    public void changePassword(String oldPassword, String newPassword) {
        checkPasswordMatch(oldPassword);
        this.password = new Password(newPassword);
    }

    public void setProfileImage(ImageId imageId, String imageUrl) {
        this.profileImage = new MemberProfileImage(imageId, imageUrl);
    }

    public void setTasteMood(TasteMoodId tasteMoodId) {
        this.tasteMoodId = tasteMoodId;
    }

    public void setProfileImage(ImageId imageId) {
        this.profileImage = new MemberProfileImage(imageId);
    }

    private MemberCreatedEvent toMemberCreatedEvent() {
        return MemberCreatedEvent.of(id, email, nickname, profileImage.getId().getValue(), tasteMoodId, LocalDateTime.now());
    }

}
