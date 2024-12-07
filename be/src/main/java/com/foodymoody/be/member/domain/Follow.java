package com.foodymoody.be.member.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member follower;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member followed;

    @CreatedDate
    private LocalDateTime createdAt;

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
