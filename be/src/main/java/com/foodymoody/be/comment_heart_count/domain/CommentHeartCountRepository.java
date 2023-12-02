package com.foodymoody.be.comment_heart_count.domain;

import com.foodymoody.be.comment.domain.entity.CommentId;
import java.util.Optional;

public interface CommentHeartCountRepository {

    Optional<CommentHeartCount> findByCommentId(CommentId commentId);

    CommentHeartCount save(CommentHeartCount commentHeartCount);
}
