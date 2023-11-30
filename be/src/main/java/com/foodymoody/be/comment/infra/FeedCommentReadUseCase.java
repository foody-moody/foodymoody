package com.foodymoody.be.comment.infra;

import com.foodymoody.be.comment.application.FeedCommentReadService;
import com.foodymoody.be.comment.application.dto.response.MemberCommentSummaryResponse;
import com.foodymoody.be.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedCommentReadUseCase {

    private final FeedCommentReadService feedCommentReadService;
    private final FeedService feedService;

    public Slice<MemberCommentSummaryResponse> fetchComments(String feedId, Pageable pageable) {
        feedService.validate(feedId);
        return feedCommentReadService.fetchComments(feedId, pageable);
    }
}
