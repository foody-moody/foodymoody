package com.foodymoody.be.comment_heart.domain;

import com.foodymoody.be.comment.domain.entity.CommentId;

public interface CommentHeartRepository {

    CommentHeart save(CommentHeart commentHeart);

    void deleteByCommentIdAndMemberId(CommentId commentId, String memberId);
}
