package com.foodymoody.be.member.repository;

import com.foodymoody.be.member.domain.Follow;
import com.foodymoody.be.member.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Slice<Follow> findByfollower(Member member, Pageable pageable);

    Slice<Follow> findByFollowed(Member member, Pageable pageable);

}
