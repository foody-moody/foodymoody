package com.foodymoody.be.feed_collection_reply.infra.persistence.jpa;

import com.foodymoody.be.common.util.ids.FeedCollectionCommentId;
import com.foodymoody.be.common.util.ids.FeedCollectionReplyId;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplaySummary;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedCollectionReplyJpaRepository extends JpaRepository<FeedCollectionReply, FeedCollectionReplyId> {

    @Query(
            "SELECT _reply.id as id,_reply.content as content,_reply.updatedAt as updatedAt,_reply.createdAt as createdAt" +
                    ",_member.id as memberId,_member.nickname as nickname,_image.url as profileUrl " +
                    "FROM FeedCollectionReply _reply " +
                    "JOIN Member _member ON _reply.memberId = _member.id " +
                    "JOIN Image _image ON _member.profileImage = _image.id " +
                    "WHERE _reply.commentId = :commentId "
    )
    Slice<FeedCollectionReplaySummary> findSummaryByCommentId(FeedCollectionCommentId commentId, Pageable pageable);
}
