package com.foodymoody.be.member.application;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowWriteService {

    private final MemberQueryService memberQueryService;

    public void follow(MemberId id, MemberId targetId) {
        Member member = memberQueryService.findById(id);
        Member target = memberQueryService.findById(targetId);
        member.follow(target);
    }

    public void unfollow(MemberId id, MemberId targetId) {
        Member member = memberQueryService.findById(id);
        Member target = memberQueryService.findById(targetId);
        member.unfollow(target);
    }

}
