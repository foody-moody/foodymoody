package com.foodymoody.be.member.application.service;

import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowWriteService {

    private final MemberReadService memberReadService;

    public void follow(MemberId id, MemberId targetId) {
        Member member = memberReadService.findById(id);
        Member target = memberReadService.findById(targetId);
        member.follow(target);
    }

    public void unfollow(MemberId id, MemberId targetId) {
        Member member = memberReadService.findById(id);
        Member target = memberReadService.findById(targetId);
        member.unfollow(target);
    }

}
