package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.exception.FollowFailedByAlreadyFollowingException;
import com.foodymoody.be.common.exception.UnfollowFailedByNotFollowingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class MyFollowings {

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Follow> follows = new ArrayList<>();

    public void add(Member me, Member target) {
        if (contains(target)) {
            throw new FollowFailedByAlreadyFollowingException();
        }
        this.follows.add(Follow.of(me, target));
    }

    public boolean contains(Member member) {
        return this.follows.stream()
                .anyMatch(follow -> follow.getFollowed().equals(member));
    }

    public void remove(Member target) {
        Follow follow = get(target)
                .orElseThrow(UnfollowFailedByNotFollowingException::new);
        follows.remove(follow);
    }

    private Optional<Follow> get(Member target) {
        return this.follows.stream()
                .filter(follow -> follow.getFollowed().equals(target))
                .findAny();
    }
}
