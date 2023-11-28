package com.foodymoody.be.heart.service;

import com.foodymoody.be.heart.domain.Heart;
import com.foodymoody.be.heart.dto.request.HeartServiceRequest;
import com.foodymoody.be.heart.dto.response.HeartResponse;
import com.foodymoody.be.heart.repository.HeartRepository;
import com.foodymoody.be.heart.util.HeartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HeartService {

    private final HeartRepository heartRepository;

    public HeartResponse like(HeartServiceRequest heartServiceRequest) {
        if (isHeartExist(heartServiceRequest)) {
            throw new IllegalArgumentException("이미 좋아요 누른 피드입니다.");
        }

        Heart heart = HeartMapper.toHeartWithFeedIdAndMemberId(heartServiceRequest.getFeedId(),
                heartServiceRequest.getMemberId());
        heart.updateCount();

        Heart savedHeart = heartRepository.save(heart);
        return HeartMapper.toHeartResponse(savedHeart);
    }

    private boolean isHeartExist(HeartServiceRequest heartServiceRequest) {
        return heartRepository.findHeartByMemberIdAndFeedId(heartServiceRequest.getMemberId(),
                        heartServiceRequest.getFeedId())
                .isPresent();
    }

}
