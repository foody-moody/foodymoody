package com.foodymoody.be.reply_heart.domain;

import com.foodymoody.be.common.util.ids.ReplyHeartId;
import com.foodymoody.be.common.util.ids.ReplyId;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReplyHeart {

    @EmbeddedId
    private ReplyHeartId id;
    @AttributeOverride(name = "value", column = @Column(name = "reply_id"))
    private ReplyId replyId;
    private String memberId;

    public ReplyHeart(ReplyHeartId id, ReplyId replyId, String memberId) {
        this.id = id;
        this.replyId = replyId;
        this.memberId = memberId;
    }
}
