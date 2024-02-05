package com.foodymoody.be.feed.application;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.foodymoody.be.common.exception.ErrorMessage;
import com.foodymoody.be.common.exception.FeedIdNotExistsException;
import com.foodymoody.be.common.util.ids.FeedId;
import com.foodymoody.be.feed.infra.persistence.FeedRepositoryImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("FeedReadService 테스트")
@ExtendWith(MockitoExtension.class)
class FeedReadServiceTest {

    @InjectMocks
    private FeedReadService feedReadService;
    @Mock
    private FeedRepositoryImpl feedRepository;

    @DisplayName("validateId()로 feedId가 DB에 저장되어 있지 않으면 FeedIdNotExistsException 예외가 발생한다.")
    @Test
    void validateId() {
        // given
        FeedId notExistsId = new FeedId("not exists");
        given(feedRepository.fetchById(notExistsId)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> feedReadService.findFeed(notExistsId))
                .isInstanceOf(FeedIdNotExistsException.class)
                .message()
                .isEqualTo(ErrorMessage.FEED_ID_NOT_EXISTS.getMessage());
    }

    @DisplayName("validateIds()로 feedId List가 DB에 저장되어 있지 않으면 FeedIdNotExistsException 예외가 발생한다.")
    @Test
    void validateIds() {
        // given
        List<FeedId> feedIds = List.of(new FeedId("not exists1"), new FeedId("not exists2"));
        feedIds.forEach(feedId -> given(feedRepository.fetchById(feedId)).willReturn(Optional.empty()));

        // when
        // then
        feedIds.forEach(feedId -> assertThatThrownBy(() -> feedReadService.findFeed(feedId))
                .isInstanceOf(FeedIdNotExistsException.class)
                .message()
                .isEqualTo(ErrorMessage.FEED_ID_NOT_EXISTS.getMessage())
        );
    }

}
