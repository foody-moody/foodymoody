package com.foodymoody.be.comment_heart.domain;

import com.foodymoody.be.common.util.ids.CommentId;

public interface CommentHeartRepository {

    CommentHeart save(CommentHeart commentHeart);

    void deleteByCommentIdAndMemberId(CommentId commentId, String memberId);

    boolean existsByCommentIdAndMemberId(CommentId commentId, String memberId);
}
