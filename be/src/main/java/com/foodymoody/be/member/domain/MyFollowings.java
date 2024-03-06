package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.exception.FollowFailedByAlreadyFollowingException;
import com.foodymoody.be.common.exception.UnfollowFailedByNotFollowingException;
import com.foodymoody.be.common.util.ids.MemberId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        if (containsById(target.getId())) {
            throw new FollowFailedByAlreadyFollowingException();
        }
        this.follows.add(Follow.of(me, target));
    }

    public boolean containsById(MemberId id) {
        return this.follows.stream()
                .anyMatch(follow -> Objects.equals(follow.getFollowed().getId(), id));
    }

    public void remove(Member target) {
        Follow follow = get(target)
                .orElseThrow(UnfollowFailedByNotFollowingException::new);
        follows.remove(follow);
    }

    public int getCount() {
        return follows.size();
    }

    private Optional<Follow> get(Member target) {
        return this.follows.stream()
                .filter(follow -> follow.getFollowed().equals(target))
                .findAny();
    }
}
