package com.foodymoody.be.feed_collection.infra.usecase.dto;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import com.foodymoody.be.feed_collection.domain.AuthorSummaryResponse;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionCommentResponse {

    private FeedCollectionCommentId id;
    private FeedCollectionId feedId;
    private Content content;
    private boolean deleted;
    private boolean hasReply;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean liked;
    private int likeCount;
    private AuthorSummaryResponse author;

    public FeedCollectionCommentResponse(
            FeedCollectionCommentId id,
            FeedCollectionId feedId,
            Content content,
            boolean deleted,
            boolean hasReply,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            boolean liked,
            int likeCount,
            MemberId memberId,
            String nickname,
            String profileImageUrl,
            String mood
    ) {
        this.id = id;
        this.feedId = feedId;
        this.content = content;
        this.deleted = deleted;
        this.hasReply = hasReply;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.liked = liked;
        this.likeCount = likeCount;
        this.author = new AuthorSummaryResponse(memberId, nickname, mood, profileImageUrl);
    }
}
