package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.domain.Follow;
import com.foodymoody.be.member.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("SELECT f.followed FROM Follow f "
            + "WHERE f.follower = :member "
            + "ORDER BY f.createdAt DESC ")
    Slice<Member> findFollowedByFollowerOrderByCreatedAtDesc(Member member, Pageable pageable);

    @Query("SELECT f.follower FROM Follow f "
            + "WHERE f.followed = :member "
            + "ORDER BY f.createdAt DESC ")
    Slice<Member> findFollowerByFollowedOrderByCreatedAtDesc(Member member, Pageable pageable);

}
