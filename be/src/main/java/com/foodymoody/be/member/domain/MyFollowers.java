package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Embeddable
public class MyFollowers {

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Follow> follows = new ArrayList<>();

    public boolean containsById(MemberId id) {
        return follows.stream()
                .anyMatch(follow -> Objects.equals(follow.getFollower().getId(), id));
    }
}
