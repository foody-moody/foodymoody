package com.foodymoody.be.heart.service;

import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.service.FeedService;
import com.foodymoody.be.heart.domain.Heart;
import com.foodymoody.be.heart.dto.request.HeartServiceRequest;
import com.foodymoody.be.heart.dto.response.HeartResponse;
import com.foodymoody.be.heart.repository.HeartRepository;
import com.foodymoody.be.heart.util.HeartMapper;
import com.foodymoody.be.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberService memberService;
    private final FeedService feedService;

    @Transactional
    public HeartResponse like(HeartServiceRequest request) {
        String memberId = request.getMemberId();
        String feedId = request.getFeedId();

        memberService.validateIdExists(memberId);

        if (existsHeart(memberId, feedId)) {
            throw new IllegalArgumentException("이미 좋아요 누른 피드입니다.");
        }

        heartRepository.save(HeartMapper.toHeartWithFeedIdAndMemberId(feedId, memberId));
        heartRepository.incrementCount(feedId);

        Heart heart = findHeart(memberId, feedId);
        Feed feed = updateFeed(feedId, heart.getCount());

        return HeartMapper.toHeartResponse(heart, feed.isLiked());
    }

    @Transactional
    public void unLike(HeartServiceRequest request) {
        String feedId = request.getFeedId();
        String memberId = memberService.findById(request.getMemberId()).getMemberId();

        Heart heart = findHeart(memberId, feedId);
        heartRepository.delete(heart);

        updateFeed(feedId, 0);
    }

    private Feed updateFeed(String feedId, int heartCount) {
        Feed feed = feedService.findFeed(feedId);

        feed.updateIsLikedBy(heartCount);
        feed.updateLikeCountBy(heartCount);

        return feed;
    }

    private Heart findHeart(String memberId, String feedId) {
        return heartRepository.findHeartByMemberIdAndFeedId(memberId, feedId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요 누른 피드가 없습니다."));
    }

    private boolean existsHeart(String memberId, String feedId) {
        return heartRepository.existsHeartByMemberIdAndFeedId(memberId, feedId);
    }

}
