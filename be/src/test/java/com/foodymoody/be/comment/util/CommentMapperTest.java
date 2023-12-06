package com.foodymoody.be.comment.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.foodymoody.be.comment.application.CommentMapper;
import com.foodymoody.be.comment.application.dto.request.RegisterCommentRequest;
import com.foodymoody.be.comment.domain.entity.Comment;
import com.foodymoody.be.common.event.Events;
import com.foodymoody.be.common.util.ids.CommentId;
import com.foodymoody.be.common.util.ids.FeedId;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("댓글 매퍼 테스트")
class CommentMapperTest {

    @BeforeEach
    void setUp() {
        Events.setPublisher(event -> {
        });
    }

    @DisplayName("toEntity() 메소드는 RegisterCommentRequest 객체와 id를 인자로 받아 Comment 객체를 반환한다.")
    @Test
    void toEntity() {
        // given
        RegisterCommentRequest request = CommentFixture.registerCommentRequest();
        CommentMapper commentMapper = new CommentMapper();
        FeedId feedId = CommentFixture.comment().getFeedId();
        LocalDateTime createdAt = CommentFixture.CREATED_AT;
        CommentId commentId = new CommentId(CommentFixture.COMMENT_ID);
        String memberId = CommentFixture.MEMBER_ID;

        // when
        Comment comment = commentMapper.toEntity(request, feedId, createdAt,
                commentId, memberId);

        // then
        Assertions.assertAll(
                () -> assertThat(comment).isNotNull(),
                () -> assertThat(comment.getId()).usingRecursiveComparison().isEqualTo(CommentFixture.commentId()),
                () -> assertThat(comment.getContent()).isEqualTo(CommentFixture.CONTENT),
                () -> assertThat(comment.getFeedId().getValue()).isEqualTo(CommentFixture.FEED_ID),
                () -> assertThat(comment.getCreatedAt()).isEqualTo(createdAt),
                () -> assertThat(comment.getUpdatedAt()).isEqualTo(createdAt),
                () -> assertThat(comment.isDeleted()).isEqualTo(CommentFixture.DELETED),
                () -> assertThat(comment.getMemberId()).isEqualTo(CommentFixture.MEMBER_ID)
        );
    }
}
