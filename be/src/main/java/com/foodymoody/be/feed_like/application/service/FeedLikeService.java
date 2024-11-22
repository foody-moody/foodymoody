package com.foodymoody.be.feed_like.application.service;

import com.foodymoody.be.common.exception.FeedHeartAlreadyExistsException;
import com.foodymoody.be.common.exception.NotFoundFeedHeartException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed.domain.entity.Feed;
import com.foodymoody.be.feed.domain.repository.FeedRepository;
import com.foodymoody.be.feed_like.application.FeedLikeMapper;
import com.foodymoody.be.feed_like.application.dto.response.FeedLikeResponse;
import com.foodymoody.be.feed_like.domain.FeedLikeRepository;
import com.foodymoody.be.feed_like.domain.entity.FeedLike;
import com.foodymoody.be.feed_like_count.application.service.FeedLikeCountService;
import com.foodymoody.be.feed_like_count.domain.entity.FeedLikeCount;
import com.foodymoody.be.member.application.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedLikeService {

    private final FeedRepository feedRepository;
    private final FeedLikeRepository feedLikeRepository;
    private final FeedLikeCountService feedLikeCountService;
    private final MemberReadService memberReadService;
    private final FeedReadService feedReadService;

    @Transactional
    public FeedLikeResponse like(String feedStringId, MemberId memberId) {
        memberReadService.validateIdExists(memberId);

        if (existsHeart(memberId, feedStringId)) {
            throw new FeedHeartAlreadyExistsException();
        }

        FeedLike feedLike = FeedLikeMapper
                .makeFeedHeartWithFeedIdAndMemberId(
                        IdFactory.createFeedHeartId(),
                        IdFactory.createFeedId(feedStringId),
                        memberId,
                        true
                );
        FeedLike savedFeedLike = feedLikeRepository.save(feedLike);

        feedLikeCountService.incrementFeedHeartCount(feedStringId);

        FeedLikeCount feedLikeCount = feedLikeCountService.findFeedHeartCountByFeedId(feedStringId);
        updateFeed(feedStringId, feedLikeCount.getCount());

        return FeedLikeMapper.toHeartResponse(
                savedFeedLike.getId().getValue(),
                savedFeedLike.getFeedId().getValue(),
                savedFeedLike.getMemberId().getValue(),
                savedFeedLike.isLiked(),
                feedLikeCount.getCount()
        );
    }

    @Transactional
    public void unLike(String feedStringId, MemberId memberId) {
        memberReadService.validateIdExists(memberId);

        if (!existsHeart(memberId, feedStringId)) {
            throw new NotFoundFeedHeartException();
        }

        feedLikeRepository.deleteByFeedIdAndMemberId(IdFactory.createFeedId(feedStringId), memberId);

        feedLikeCountService.decrementFeedHeartCount(feedStringId);

        FeedLikeCount feedLikeCount = feedLikeCountService.findFeedHeartCountByFeedId(feedStringId);
        updateFeed(feedStringId, feedLikeCount.getCount());
    }

    private void updateFeed(String feedId, int heartCount) {
        FeedId feedIdObj = IdFactory.createFeedId(feedId);
        feedRepository.updateLikeCount(heartCount, feedIdObj);
    }

    @Transactional(readOnly = true)
    public boolean existsHeart(MemberId memberId, String feedId) {
        return feedLikeRepository.existsByMemberIdAndFeedId(memberId, IdFactory.createFeedId(feedId));
    }

    @Transactional(readOnly = true)
    public boolean fetchIsLiked(FeedId feedId, MemberId memberId) {
        return feedLikeRepository.fetchIsLiked(feedId, memberId);
    }

}
