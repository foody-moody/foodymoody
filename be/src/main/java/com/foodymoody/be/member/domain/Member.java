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
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Member {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private MemberId id;
    private String email;
    private String nickname;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password"))
    private Password password;
    @Embedded
    private MemberProfileImage profileImage;
    @ManyToOne(fetch = FetchType.LAZY)
    private TasteMood tasteMood;
    @Embedded
    private MyFollowings myFollowings;
    @Embedded
    private MyFollowers myFollowers;

    private Member(MemberId id, String email, String nickname, String password, TasteMood tasteMood) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = new Password(password);
        this.tasteMood = tasteMood;
        this.profileImage = new MemberProfileImage(ImageId.MEMBER_PROFILE_DEFAULT);
        Events.raise(toMemberCreatedEvent());
    }

    public static Member of(
            String id, String email, String nickname, String password, String reconfirmPassword,
            TasteMood tasteMood
    ) {
        if (!Objects.equals(reconfirmPassword, password)) {
            throw new InvalidReconfirmPasswordException();
        }
        return new Member(new MemberId(id), email, nickname, password, tasteMood);
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

    public ImageId getProfileImageId() {
        return profileImage.getId();
    }

    public TasteMoodId getTasteMoodId() {
        return tasteMood.getId();
    }

    public void checkPasswordMatch(String password) {
        this.password.validateEquals(password);
    }

    public void changePassword(String oldPassword, String newPassword) {
        checkPasswordMatch(oldPassword);
        this.password = new Password(newPassword);
    }

    public void updateProfileImage(ImageId imageId) {
        this.profileImage = new MemberProfileImage(imageId);
    }

    public void changeTasteMood(TasteMood tasteMood) {
        this.tasteMood = tasteMood;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void follow(Member target) {
        if (Objects.isNull(target) || Objects.equals(target, this)) {
            throw new IllegalArgumentException("팔로우할 수 없는 회원입니다");
        }
        this.myFollowings.add(this, target);
        Events.raise(toMemberFollowedEvent(target));
    }

    public void unfollow(Member target) {
        if (Objects.isNull(target) || Objects.equals(target, this)) {
            throw new IllegalArgumentException("언팔로우할 수 없는 회원입니다");
        }
        this.myFollowings.remove(target);
    }

    public boolean isMyFollowing(MemberId id) {
        return myFollowings.containsById(id);
    }

    public boolean isMyFollower(MemberId id) {
        return myFollowers.containsById(id);
    }

    private MemberFollowedEvent toMemberFollowedEvent(Member target) {
        return MemberFollowedEvent.of(
                target.id,
                this.id,
                LocalDateTime.now()
        );
    }

    private MemberCreatedEvent toMemberCreatedEvent() {
        return MemberCreatedEvent.of(id, email, nickname, profileImage.getId(), tasteMood.getId(), LocalDateTime.now());
    }

}
