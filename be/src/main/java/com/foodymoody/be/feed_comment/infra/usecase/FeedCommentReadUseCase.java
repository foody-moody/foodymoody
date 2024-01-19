package com.foodymoody.be.feed_comment.infra.usecase;

import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed.application.FeedReadService;
import com.foodymoody.be.feed_comment.application.CommentMapper;
import com.foodymoody.be.feed_comment.application.FeedCommentReadService;
import com.foodymoody.be.feed_comment.application.dto.response.MemberCommentSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedCommentReadUseCase {

    private final FeedCommentReadService feedCommentReadService;
    private final FeedReadService feedReadService;

    public Slice<MemberCommentSummaryResponse> fetchComments(FeedId feedId, Pageable pageable) {
        feedReadService.validateId(feedId);
        return CommentMapper.mapToSummaryResponse(feedCommentReadService.fetchComments(feedId, pageable));
    }

    public Slice<MemberCommentSummaryResponse> fetchComments(FeedId feedId, Pageable pageable, MemberId memberId) {
        feedReadService.validateId(feedId);
        return CommentMapper.mapToSummaryResponse(feedCommentReadService.fetchComments(feedId, pageable, memberId));
    }
}
