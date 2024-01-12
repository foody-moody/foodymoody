package com.foodymoody.be.comment.application.dto.data;

import com.foodymoody.be.common.util.Content;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;
import lombok.Getter;

@Getter
public class RegisterReplyData {

    private final CommentId commentId;
    private final MemberId memberId;
    private final Content content;

    public RegisterReplyData(CommentId commentId, MemberId memberId, Content content) {
        this.commentId = commentId;
        this.memberId = memberId;
        this.content = content;
    }
}
