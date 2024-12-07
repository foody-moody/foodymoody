package com.foodymoody.be.feed_collection_reply.application.usecase;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class FeedCollectionReplyResponse {

    private FeedCollectionReplyId id;
    private Content content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private MemberResponse member;
    private boolean isLiked;
    private long likeCount;

    public FeedCollectionReplyResponse(
            FeedCollectionReplyId id,
            Content content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            MemberId memberId,
            String nickname,
            String profileUrl,
            boolean isLiked,
            long likeCount
    ) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isLiked = isLiked;
        this.likeCount = likeCount;
        this.member = new MemberResponse(memberId, nickname, profileUrl);
    }

    @Getter
    class MemberResponse {

        private MemberId memberId;
        private String nickname;
        private String profileUrl;

        public MemberResponse(MemberId memberId, String nickname, String profileUrl) {
            this.memberId = memberId;
            this.nickname = nickname;
            this.profileUrl = profileUrl;
        }

    }

}
