package com.foodymoody.be.feed_collection_comment.domain;

import com.foodymoody.be.common.util.CommentContent;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionId;
import com.foodymoody.be.common.util.ids.MemberId;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCollectionComment {

    @Id
    private FeedCollectionCommentId id;
    @AttributeOverride(name = "value", column = @Column(name = "feed_id"))
    private FeedCollectionId feedId;
    @AttributeOverride(name = "value", column = @Column(name = "member_id"))
    private MemberId memberId;
    @Embedded
    private CommentContent content;
    private boolean deleted;
    private boolean hasReply;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private FeedCollectionReplyIds replyIds;

    public FeedCollectionComment(
            FeedCollectionCommentId id, FeedCollectionId feedId, MemberId memberId, CommentContent content,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.feedId = feedId;
        this.memberId = memberId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public FeedCollectionCommentId getId() {
        return id;
    }

    public void delete(MemberId memberId) {
        if (memberId.isSame(this.memberId)) {
            this.deleted = true;
            return;
        }
        throw new IllegalArgumentException("삭제 권한이 없습니다.");
    }
}
