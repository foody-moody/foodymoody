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
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberService memberService;
    private final FeedService feedService;

    public HeartResponse like(HeartServiceRequest request) {
        if (isHeartExist(request)) {
            throw new IllegalArgumentException("이미 좋아요 누른 피드입니다.");
        }

        String feedId = request.getFeedId();
        String memberId = memberService.findById(request.getMemberId()).getMemberId();

        Heart heart = HeartMapper.toHeartWithFeedIdAndMemberId(feedId,
                memberId);
        heart.updateCount();

        Heart savedHeart = heartRepository.save(heart);

        Feed feed = updateFeed(feedId, true);

        return HeartMapper.toHeartResponse(savedHeart, feed.isLiked());
    }

    private boolean isHeartExist(HeartServiceRequest heartServiceRequest) {
        return heartRepository.findHeartByMemberIdAndFeedId(heartServiceRequest.getMemberId(),
                        heartServiceRequest.getFeedId())
                .isPresent();
    }

    public void unLike(HeartServiceRequest request) {
        String feedId = request.getFeedId();
        String memberId = memberService.findById(request.getMemberId()).getMemberId();

        Heart heart = findHeart(memberId, feedId);
        heartRepository.delete(heart);

        updateFeed(feedId, false);
    }

    private Feed updateFeed(String feedId, boolean flag) {
        Feed feed = feedService.findFeed(feedId);
        feed.updateIsLikedBy(flag);
        // TODO: 5초에 한번씩 한꺼번에 업데이트하도록 하기
        feed.updateLikeCountBy(flag);

        return feed;
    }

    private Heart findHeart(String memberId, String feedId) {
        return heartRepository.findHeartByMemberIdAndFeedId(memberId, feedId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요 누른 피드가 없습니다."));
    }

}
