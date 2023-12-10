package com.foodymoody.be.member.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member follower;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member followed;

    public Follow(Member follower, Member followed) {
        this.follower = follower;
        this.followed = followed;
    }

    public static Follow of(Member follower, Member followee) {
        return new Follow(follower, followee);
    }

    public Member getFollower() {
        return follower;
    }

    public Member getFollowed() {
        return followed;
    }
}
