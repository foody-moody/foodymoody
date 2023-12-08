package com.foodymoody.be.feed.service;

import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.domain.Feed;
import com.foodymoody.be.feed.dto.response.FeedImageMenuResponse;
import com.foodymoody.be.feed.dto.response.FeedMemberResponse;
import com.foodymoody.be.feed.dto.response.FeedReadAllResponse;
import com.foodymoody.be.feed.dto.response.FeedReadResponse;
import com.foodymoody.be.feed.dto.response.FeedTasteMoodResponse;
import com.foodymoody.be.feed.repository.FeedRepository;
import com.foodymoody.be.feed.repository.dto.MemberProfileFeedPreviewResponse;
import com.foodymoody.be.feed.util.FeedMapper;
import com.foodymoody.be.member.repository.MemberFeedData;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FeedService {

    private final FeedRepository feedRepository;

    @Transactional
    public Feed save(Feed feed) {
        return feedRepository.save(feed);
    }

    @Transactional
    public void deleteById(FeedId feedId) {
        feedRepository.deleteById(feedId);
    }

    public boolean exists(String feedId) {
        return feedRepository.existsById(IdFactory.createFeedId(feedId));
    }

    public void validate(String feedId) {
        if (!exists(feedId)) {
            throw new FeedIdNotExistsException();
        }
    }

    public Feed findFeed(FeedId id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 피드가 존재하지 않습니다."));
    }

    public Slice<Feed> findAll(Pageable pageable) {
        return feedRepository.findAll(pageable);
    }

    public Slice<MemberProfileFeedPreviewResponse> findPreviewsByMemberId(MemberId memberId, Pageable pageable) {
        return feedRepository.fetchPreviewsByMemberId(memberId, pageable);
    }

}
