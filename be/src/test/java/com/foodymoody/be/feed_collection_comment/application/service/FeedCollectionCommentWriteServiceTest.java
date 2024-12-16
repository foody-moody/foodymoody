package com.foodymoody.be.feed_collection_comment.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.foodymoody.be.common.exception.FeedCollectionCommentNotFoundException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedCollectionCommentWriteServiceTest {

    @InjectMocks
    private FeedCollectionCommentWriteService service;

    @Mock
    private FeedCollectionCommentRepository repository;

    @DisplayName("피드 컬렉션 댓글을 가져올 때 존재하지 않는 댓글이면 예외가 발생한다.")
    @Test
    void getFeedCollectionComment() {
        // given
        var id = IdFactory.createFeedCollectionCommentId();

        // when
        assertThatThrownBy(() -> service.getFeedCollectionComment(id))
                .isInstanceOf(FeedCollectionCommentNotFoundException.class);
    }

}
