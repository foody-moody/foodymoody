package com.foodymoody.be.feed_heart.service;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.application.FeedService;
import com.foodymoody.be.feed_heart.domain.FeedHeart;
import com.foodymoody.be.feed_heart.dto.response.FeedHeartResponse;
import com.foodymoody.be.feed_heart.repository.FeedHeartRepository;
import com.foodymoody.be.feed_heart.util.FeedHeartMapper;
import com.foodymoody.be.feed_heart_count.domain.FeedHeartCount;
import com.foodymoody.be.feed_heart_count.service.FeedHeartCountService;
import com.foodymoody.be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedHeartService {

    private final FeedHeartRepository feedHeartRepository;
    private final FeedHeartCountService feedHeartCountService;
    private final MemberService memberService;
    private final FeedService feedService;

    @Transactional
    public FeedHeartResponse like(String feedStringId, String memberStringId) {
        var memberId = IdFactory.createMemberId(memberStringId);

        memberService.validateIdExists(memberId);

        if (existsHeart(memberId, feedStringId)) {
            throw new IllegalArgumentException("이미 좋아요 누른 피드입니다.");
        }

        FeedHeart feedHeart = FeedHeartMapper
                .makeFeedHeartWithFeedIdAndMemberId(IdFactory.createFeedHeartId(), IdFactory.createFeedId(feedStringId),
                        memberId);
        FeedHeart savedFeedHeart = feedHeartRepository.save(feedHeart);

        feedHeartCountService.incrementFeedHeartCount(feedStringId);

        FeedHeartCount feedHeartCount = feedHeartCountService.findFeedHeartCountByFeedId(feedStringId);
        Feed feed = updateFeed(feedStringId, feedHeartCount.getCount(), true);

        return FeedHeartMapper.toHeartResponse(savedFeedHeart.getId().getValue(), savedFeedHeart.getFeedId().getValue(),
                savedFeedHeart.getMemberId().getValue(), feed.isLiked(), feedHeartCount.getCount());
    }

    @Transactional
    public void unLike(String feedStringId, String memberStringId) {
        var memberId = IdFactory.createMemberId(memberStringId);

        memberService.validateIdExists(memberId);

        if (!existsHeart(memberId, feedStringId)) {
            throw new IllegalArgumentException("좋아요 기록이 없어 취소할 수 없습니다.");
        }

        feedHeartRepository.deleteByFeedIdAndMemberId(IdFactory.createFeedId(feedStringId), memberId);

        feedHeartCountService.decrementFeedHeartCount(feedStringId);

        FeedHeartCount feedHeartCount = feedHeartCountService.findFeedHeartCountByFeedId(feedStringId);
        updateFeed(feedStringId, feedHeartCount.getCount(), false);
    }

    private Feed updateFeed(String feedId, int heartCount, boolean isLiked) {
        FeedId feedIdObj = IdFactory.createFeedId(feedId);
        Feed feed = feedService.findFeed(feedIdObj);

        feed.updateIsLikedBy(isLiked);
        feed.updateLikeCountBy(heartCount);

        return feed;
    }

    private boolean existsHeart(MemberId memberId, String feedId) {
        return feedHeartRepository.existsHeartByMemberIdAndFeedId(memberId, IdFactory.createFeedId(feedId));
    }

}
