package com.foodymoody.be.feed_collection_comment_like.domain;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionCommentLikeId;
import com.foodymoody.be.common.util.ids.MemberId;
import javax.persistence.AttributeOverride;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FeedCollectionCommentLike {

    @Getter
    @Id
    private FeedCollectionCommentLikeId id;
    @AttributeOverride(name = "value", column = @javax.persistence.Column(name = "comment_id"))
    private FeedCollectionCommentId commentId;
    @AttributeOverride(name = "value", column = @javax.persistence.Column(name = "member_id"))
    private MemberId memberId;

    public FeedCollectionCommentLike(
            FeedCollectionCommentLikeId id, FeedCollectionCommentId commentId, MemberId memberId
    ) {
        this.id = id;
        this.commentId = commentId;
        this.memberId = memberId;
    }
}
