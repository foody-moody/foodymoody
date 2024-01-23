package com.foodymoody.be.feed_comment.application.dto.response;

import com.foodymoody.be.common.util.Content;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberReplySummaryResponse {

    private String id;
    private Content content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberSummaryResponse member;
    private long likeCount;
    private boolean liked;

    public MemberReplySummaryResponse(
            String id, Content content, LocalDateTime createdAt, LocalDateTime updatedAt,
            MemberSummaryResponse member, long heartCount, boolean hearted) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
        this.likeCount = heartCount;
        this.liked = hearted;
    }
}
