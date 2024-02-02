package com.foodymoody.be.feed_comment.persentation;

import com.foodymoody.be.common.annotation.CurrentMemberId;
import com.foodymoody.be.common.util.IdResponse;
import com.foodymoody.be.common.util.ids.FeedCommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_comment.application.dto.request.EditFeedCommentRequest;
import com.foodymoody.be.feed_comment.application.dto.request.RegisterFeedCommentRequest;
import com.foodymoody.be.feed_comment.application.dto.request.RegisterFeedReplyRequest;
import com.foodymoody.be.feed_comment.application.service.FeedCommentWriteService;
import com.foodymoody.be.feed_comment.application.usecase.FeedCommentWriteUseCase;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeedCommentWriteController {

    private final FeedCommentWriteService service;
    private final FeedCommentWriteUseCase useCase;

    @PostMapping("/api/comments")
    public ResponseEntity<IdResponse> register(
            @Valid @RequestBody RegisterFeedCommentRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var data = FeedCommentTranslator.toRegisterCommentData(request, memberId);
        var id = useCase.registerComment(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(id));
    }

    @PutMapping("/api/comments/{id}")
    public ResponseEntity<Void> edit(
            @PathVariable FeedCommentId id,
            @Valid @RequestBody EditFeedCommentRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var data = FeedCommentTranslator.toEditCommentData(request, id, memberId);
        service.edit(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable FeedCommentId id,
            @CurrentMemberId MemberId memberId
    ) {
        service.delete(id, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/comments/{id}")
    public ResponseEntity<IdResponse> reply(
            @PathVariable FeedCommentId id,
            @Valid @RequestBody RegisterFeedReplyRequest request,
            @CurrentMemberId MemberId memberId
    ) {
        var data = FeedCommentTranslator.toRegisterReplyData(request, memberId, id);
        var replyId = service.reply(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(IdResponse.of(replyId));
    }
}