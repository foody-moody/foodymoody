package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.auth.AuthProvider;
import com.foodymoody.be.common.auth.SupportedAuthProvider;
import com.foodymoody.be.common.event.EventManager;
import com.foodymoody.be.common.exception.IncorrectMemberPasswordException;
import com.foodymoody.be.common.util.ids.ImageId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.common.util.ids.TasteMoodId;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Member {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private MemberId id;
    private String email;
    private String nickname;
    private String password;

    @Embedded
    private MemberProfileImage profileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private TasteMood tasteMood;

    @Embedded
    private MyFollowings myFollowings;

    @Embedded
    private MyFollowers myFollowers;

    @Enumerated(EnumType.STRING)
    private SupportedAuthProvider authProvider;
    private LocalDateTime createdAt;

    @Builder
    public Member(
            MemberId id,
            SupportedAuthProvider authProvider,
            String email,
            String nickname,
            String password,
            TasteMood tasteMood,
            MemberProfileImage profileImage,
            LocalDateTime createdAt) {
        this.id = id;
        this.authProvider = authProvider;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.tasteMood = tasteMood;
        this.profileImage = Objects.isNull(profileImage) ? MemberProfileImage.DEFAULT : profileImage;
        this.myFollowings = new MyFollowings();
        this.myFollowers = new MyFollowers();
        this.createdAt = createdAt;
        EventManager.raise(toMemberCreatedEvent());
    }

    public static Member of(
            MemberId id,
            SupportedAuthProvider authProvider,
            String email,
            String nickname,
            String password,
            MemberProfileImage profileImage,
            TasteMood tasteMood,
            LocalDateTime now) {
        return new Member(id, authProvider, email, nickname, password, tasteMood, profileImage, now);
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

    public String getProfileImageUrl() {
        return profileImage.getUrl();
    }

    public AuthProvider getAuthProvider() {
        return this.authProvider;
    }

    public TasteMoodId getTasteMoodId() {
        if (Objects.nonNull(tasteMood)) {
            return tasteMood.getId();
        }
        return null;
    }

    public void changePassword(String oldPassword, String newPassword) {
        checkPasswordMatch(oldPassword);
        this.password = newPassword;
    }

    public void checkPasswordMatch(String password) {
        if (!Objects.equals(password, this.password)) {
            throw new IncorrectMemberPasswordException();
        }
    }

    public void updateProfileImage(MemberProfileImage newProfileImage) {
        this.profileImage = newProfileImage;
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
        EventManager.raise(toMemberFollowedEvent(target));
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

    public List<Member> getFollowers() {
        return myFollowers.getAll();
    }

    public String getTasteMoodName() {
        if (Objects.nonNull(tasteMood)) {
            return tasteMood.getName();
        }
        return null;
    }

    public int getMyFollowingCount() {
        return myFollowings.getCount();
    }

    public int getMyFollowerCount() {
        return myFollowers.getCount();
    }

    public boolean isFollowing(Member target) {
        return myFollowings.contains(target);
    }

    private MemberFollowedEvent toMemberFollowedEvent(Member target) {
        return MemberFollowedEvent.of(
                this.id,
                target.id,
                LocalDateTime.now()
        );
    }

    private MemberCreatedEvent toMemberCreatedEvent() {
        return MemberCreatedEvent.of(id, email, nickname, getProfileImageId(), getTasteMoodId(), LocalDateTime.now());
    }

}
