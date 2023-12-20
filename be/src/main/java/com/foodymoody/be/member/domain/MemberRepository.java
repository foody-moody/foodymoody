package com.foodymoody.be.member.domain;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.application.dto.FeedAuthorSummary;
import com.foodymoody.be.member.application.dto.response.MemberProfileResponse;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

public interface MemberRepository {

    Optional<MemberProfileResponse> fetchMemberProfileById(@Param("id") MemberId id, @Param("loginId") String loginId);

    Optional<FeedAuthorSummary> fetchFeedDataById(@Param("id") MemberId id);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Member save(Member entity);

    void delete(Member member);

    boolean existsById(MemberId id);

    Optional<Member> findById(MemberId id);
}
