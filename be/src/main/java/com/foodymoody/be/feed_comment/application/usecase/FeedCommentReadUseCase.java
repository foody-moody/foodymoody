package com.foodymoody.be.feed_comment.application.usecase;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.service.FeedReadService;
import com.foodymoody.be.feed_comment.application.dto.response.MemberFeedCommentSummaryResponse;
import com.foodymoody.be.feed_comment.application.mapper.FeedCommentMapper;
import com.foodymoody.be.feed_comment.application.service.FeedCommentReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedCommentReadUseCase {

    private final FeedCommentReadService feedCommentService;
    private final FeedReadService feedService;

    public Slice<MemberFeedCommentSummaryResponse> fetchComments(FeedId feedId, Pageable pageable) {
        feedService.validateId(feedId);
        var fetchComments = feedCommentService.fetchComments(feedId, pageable);
        return FeedCommentMapper.toSummaryResponses(fetchComments);
    }

    public Slice<MemberFeedCommentSummaryResponse> fetchComments(FeedId feedId, Pageable pageable, MemberId memberId) {
        feedService.validateId(feedId);
        var fetchComments = feedCommentService.fetchComments(feedId, pageable, memberId);
        return FeedCommentMapper.toSummaryResponses(fetchComments);
    }

}
