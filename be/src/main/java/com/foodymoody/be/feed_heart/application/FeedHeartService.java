package com.foodymoody.be.feed_heart.application;

import com.foodymoody.be.common.exception.FeedHeartAlreadyExistsException;
import com.foodymoody.be.common.exception.NotFoundFeedHeartException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed_heart.application.dto.response.FeedHeartResponse;
import com.foodymoody.be.feed_heart.domain.FeedHeartRepository;
import com.foodymoody.be.feed_heart.domain.entity.FeedHeart;
import com.foodymoody.be.feed_heart_count.application.FeedHeartCountService;
import com.foodymoody.be.feed_heart_count.domain.entity.FeedHeartCount;
import com.foodymoody.be.member.application.MemberReadService;
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
    private final MemberReadService memberReadService;
    private final FeedReadService feedReadService;

    @Transactional
    public FeedHeartResponse like(String feedStringId, MemberId memberId) {
        memberReadService.validateIdExists(memberId);

        if (existsHeart(memberId, feedStringId)) {
            throw new FeedHeartAlreadyExistsException();
        }

        FeedHeart feedHeart = FeedHeartMapper
                .makeFeedHeartWithFeedIdAndMemberId(IdFactory.createFeedHeartId(), IdFactory.createFeedId(feedStringId),
                        memberId, true);
        FeedHeart savedFeedHeart = feedHeartRepository.save(feedHeart);

        feedHeartCountService.incrementFeedHeartCount(feedStringId);

        FeedHeartCount feedHeartCount = feedHeartCountService.findFeedHeartCountByFeedId(feedStringId);
        updateFeed(feedStringId, feedHeartCount.getCount());

        return FeedHeartMapper.toHeartResponse(savedFeedHeart.getId().getValue(), savedFeedHeart.getFeedId().getValue(),
                savedFeedHeart.getMemberId().getValue(), savedFeedHeart.isLiked(), feedHeartCount.getCount());
    }

    @Transactional
    public void unLike(String feedStringId, MemberId memberId) {
        memberReadService.validateIdExists(memberId);

        if (!existsHeart(memberId, feedStringId)) {
            throw new NotFoundFeedHeartException();
        }

        feedHeartRepository.deleteByFeedIdAndMemberId(IdFactory.createFeedId(feedStringId), memberId);

        feedHeartCountService.decrementFeedHeartCount(feedStringId);

        FeedHeartCount feedHeartCount = feedHeartCountService.findFeedHeartCountByFeedId(feedStringId);
        updateFeed(feedStringId, feedHeartCount.getCount());
    }

    private void updateFeed(String feedId, int heartCount) {
        FeedId feedIdObj = IdFactory.createFeedId(feedId);
        Feed feed = feedReadService.findFeed(feedIdObj);

        feed.updateLikeCountBy(heartCount);
    }

    public boolean existsHeart(MemberId memberId, String feedId) {
        return feedHeartRepository.existsHeartByMemberIdAndFeedId(memberId, IdFactory.createFeedId(feedId));
    }

}
