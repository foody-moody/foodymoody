package com.foodymoody.be.feed_collection_comment.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.foodymoody.be.common.exception.FeedCollectionCommentNotFoundException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_comment.domain.FeedCollectionCommentRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedCollectionCommentReadServiceTest {

    @InjectMocks
    private FeedCollectionCommentReadService service;

    @Mock
    private FeedCollectionCommentRepository repository;

    @DisplayName("존재하지 않는 댓글 검증 시 예외 발생")
    @Test
    void validateExistence() {
        // given
        var commentId = IdFactory.createFeedCollectionCommentId();
        given(repository.existsById(commentId)).willReturn(false);

        // when, then
        assertThatThrownBy(() -> service.validateExistence(commentId))
                .isInstanceOf(FeedCollectionCommentNotFoundException.class);
    }

    @DisplayName("존재하지 않는 댓글 조회 시 예외 발생")
    @Test
    void findById() {
        // given
        var commentId = IdFactory.createFeedCollectionCommentId();
        given(repository.findById(commentId)).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> service.findById(commentId))
                .isInstanceOf(FeedCollectionCommentNotFoundException.class);
    }
}
