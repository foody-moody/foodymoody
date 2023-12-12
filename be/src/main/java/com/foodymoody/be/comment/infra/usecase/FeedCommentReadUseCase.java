package com.foodymoody.be.comment.infra.usecase;

import com.foodymoody.be.comment.application.CommentMapper;
import com.foodymoody.be.comment.application.FeedCommentReadService;
import com.foodymoody.be.comment.application.dto.response.MemberCommentSummaryResponse;
import com.foodymoody.be.feed.application.FeedReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedCommentReadUseCase {

    private final FeedCommentReadService feedCommentReadService;
    private final FeedReadService feedReadService;

    public Slice<MemberCommentSummaryResponse> fetchComments(String feedId, Pageable pageable) {
        feedReadService.validateId(feedId);
        return CommentMapper.mapToSummaryResponse(feedCommentReadService.fetchComments(feedId, pageable));
    }

    public Slice<MemberCommentSummaryResponse> fetchComments(String feedId, Pageable pageable, String memberId) {
        feedReadService.validateId(feedId);
        return CommentMapper.mapToSummaryResponse(feedCommentReadService.fetchComments(feedId, pageable, memberId));
    }
}
