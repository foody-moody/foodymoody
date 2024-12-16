package com.foodymoody.be.feed_collection_reply.application.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.foodymoody.be.common.exception.FeedCollectionReplyNotFoundException;
import com.foodymoody.be.common.util.ids.IdFactory;
import com.foodymoody.be.feed_collection_reply.domain.FeedCollectionReplyRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FeedCollectionReplyWriteServiceTest {

    @InjectMocks
    FeedCollectionReplyWriteService service;

    @Mock
    FeedCollectionReplyRepository repository;

    @DisplayName("컬렉션 대댓글 조회 시 존재하지 않으면 예외를 던진다")
    @Test
    void fetchByIdWithNotExists() {
        // given
        var replyId = IdFactory.createFeedCollectionReplyId();
        given(repository.findById(replyId)).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> service.fetchById(replyId))
                .isInstanceOf(FeedCollectionReplyNotFoundException.class);

    }

}
