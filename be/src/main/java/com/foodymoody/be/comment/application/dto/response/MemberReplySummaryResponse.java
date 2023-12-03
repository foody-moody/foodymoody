package com.foodymoody.be.comment.application.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberReplySummaryResponse {

    private String id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberSummaryResponse member;
    private long heartCount;
    private boolean hearted;

    public MemberReplySummaryResponse(String id, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
            MemberSummaryResponse member, long heartCount, boolean hearted) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.member = member;
        this.heartCount = heartCount;
        this.hearted = hearted;
    }
}
