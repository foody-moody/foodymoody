package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.domain.Follow;
import com.foodymoody.be.member.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT new com.foodymoody.be.member.repository.FollowInfoMember(following.id, following.nickname, i.url) "
            + "FROM Follow f "
            + "INNER JOIN Member following ON f.followed = following "
            + "INNER JOIN Image i ON i.id = following.profileImage.id "
            + "WHERE f.follower = :member "
            + "ORDER BY f.createdAt DESC ")
    Slice<FollowInfoMember> findFollowedByFollowerOrderByCreatedAtDesc(Member member, Pageable pageable);

    @Query("SELECT new com.foodymoody.be.member.repository.FollowInfoMember(follower.id, follower.nickname, i.url) "
            + "FROM Follow f "
            + "INNER JOIN Member follower ON f.follower = follower "
            + "INNER JOIN Image i ON i.id = follower.profileImage.id "
            + "WHERE f.followed = :member "
            + "ORDER BY f.createdAt DESC ")
    Slice<FollowInfoMember> findFollowerByFollowedOrderByCreatedAtDesc(Member member, Pageable pageable);

}
