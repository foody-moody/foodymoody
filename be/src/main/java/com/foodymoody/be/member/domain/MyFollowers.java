package com.foodymoody.be.member.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class MyFollowers {

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Follow> follows = new ArrayList<>();

    public boolean contains(Member member) {
        return follows.stream()
                .anyMatch(follow -> follow.getFollower().equals(member));
    }
}
