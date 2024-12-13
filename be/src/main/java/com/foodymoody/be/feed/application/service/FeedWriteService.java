package com.foodymoody.be.feed.application.service;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.repository.FeedRepository;
import com.foodymoody.be.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class FeedWriteService {

    private final FeedRepository feedRepository;

    public Feed save(Feed feed) {
        return feedRepository.save(feed);
    }

    public void deleteById(FeedId feedId) {
        feedRepository.deleteById(feedId);
    }

    public void deleteByMember(Member member) {
        feedRepository.deleteAll(member);
    }

}
