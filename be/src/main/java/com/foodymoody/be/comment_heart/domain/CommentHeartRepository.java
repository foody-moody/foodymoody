package com.foodymoody.be.comment_heart.domain;

import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.MemberId;

public interface CommentHeartRepository {

    CommentHeart save(CommentHeart commentHeart);

    void deleteByCommentIdAndMemberId(CommentId commentId, MemberId memberId);

    boolean existsByCommentIdAndMemberId(CommentId commentId, MemberId memberId);
}
