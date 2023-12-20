package com.foodymoody.be.member.domain;

import com.foodymoody.be.member.application.dto.FollowMemberSummary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FollowRepository {

    Slice<FollowMemberSummary> findFollowedByFollowerOrderByCreatedAtDesc(Member member, Pageable pageable);

    Slice<FollowMemberSummary> findFollowerByFollowedOrderByCreatedAtDesc(Member member, Pageable pageable);

}
