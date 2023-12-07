package com.foodymoody.be.comment_heart_count.domain;

import com.foodymoody.be.common.util.ids.CommentId;

public interface CommentHeartCountRepository {

    CommentHeartCount save(CommentHeartCount commentHeartCount);

    void incrementCount(CommentId commentId);

    void decrementCount(CommentId commentId);
}
